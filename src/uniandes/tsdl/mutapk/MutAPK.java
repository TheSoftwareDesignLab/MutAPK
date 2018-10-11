package uniandes.tsdl.mutapk;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.antlr.smaliParser;
import uniandes.tsdl.jflex.smaliFlexLexer;
import uniandes.tsdl.mutapk.detectors.MutationLocationDetector;
import uniandes.tsdl.mutapk.detectors.MutationLocationListBuilder;
import uniandes.tsdl.mutapk.helper.APKToolWrapper;
import uniandes.tsdl.mutapk.model.MutationType;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.OperatorBundle;
import uniandes.tsdl.mutapk.processors.MutationsProcessor;
import uniandes.tsdl.mutapk.processors.SourceCodeProcessor;
import uniandes.tsdl.mutapk.processors.TextBasedDetectionsProcessor;
import uniandes.tsdl.smali.LexerErrorInterface;

public class MutAPK {

	public static void main(String[] args) {
		try {
			// long initialTime = System.currentTimeMillis();
			// System.out.println(initialTime);
			runMutAPK(args);
			// long finalTime = System.currentTimeMillis();
			// System.out.println(finalTime);
			// System.out.println(finalTime-initialTime);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void runMutAPK(String[] args) throws Exception {
		//Usage Error
		if (args.length != 6) {
			System.out.println("******* ERROR: INCORRECT USAGE *******");
			System.out.println("Argument List:");
			System.out.println("1. APK path");
			System.out.println("2. Package Name");
			System.out.println("3. Mutants path");
			System.out.println("4. Binaries path");
			System.out.println("5. Directory containing the operator.properties file");
			System.out.println("6. Multithread generation (true/false)");
			return;
		}
		
		//Getting arguments
		String apkName;
		String apkPath = args[0];
		String appName = args[1];
		String mutantsFolder = args[2];
		String extraPath = args[3];
		String operatorsDir = args[4];
		boolean multithread = Boolean.parseBoolean(args[5]);

		
		// Fix params based in OS
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			mutantsFolder = mutantsFolder.replaceAll("/", File.separator)+File.separator;
			extraPath = extraPath.replaceAll("/", File.separator)+File.separator;
			apkPath = apkPath.replaceAll("/", File.separator);
			apkName = apkPath.substring(apkPath.lastIndexOf("\\"));
		} else {
			apkName = apkPath.substring(apkPath.lastIndexOf("/"));
		}
		// Decode the APK
		APKToolWrapper.openAPK(apkPath, extraPath);

		//Read selected operators
		OperatorBundle operatorBundle = new OperatorBundle(operatorsDir);
		System.out.println(operatorBundle.printSelectedOperators());

		//Text-Based operators selected
		List<MutationLocationDetector> textBasedDetectors = operatorBundle.getTextBasedDetectors();
		
		//1. Run detection phase for Text-based detectors
		HashMap<MutationType, List<MutationLocation>> locations = TextBasedDetectionsProcessor.process("temp", textBasedDetectors);
		

		// //2. Run detection phase for AST-based detectors
		// //2.1 Preprocessing: Find locations to target API calls (including calls to constructors)
		// //SourceCodeProcessor scp = SourceCodeProcessor.getInstance(); (not safe, if MPlus is executed on different apps)
		SourceCodeProcessor scp = new SourceCodeProcessor(operatorBundle);
		locations.putAll( scp.processFolder("temp", extraPath, appName));

		// //2.2. Call the detectors on each location in order to find any extra information required for each case.
//		locations = scp.findExtraInfoRequired(locations);

		Set<MutationType> keys = locations.keySet();
		List<MutationLocation> list = null;
		System.out.println("Amount Mutants	Mutation Operator");
		for (MutationType mutationType : keys) {
			list = locations.get(mutationType);
			System.out.println(list.size()+"		"+mutationType);
//			for (MutationLocation mutationLocation : list) {
//				System.out.println("File: "+mutationLocation.getFilePath()+", start line:" + mutationLocation.getStartLine()+", end line: "+mutationLocation.getEndLine()+", start column"+mutationLocation.getStartColumn());
//			}
		}
		
		//3. Build MutationLocation List
		List<MutationLocation> mutationLocationList = MutationLocationListBuilder.buildList(locations);
		System.out.println("Total Locations: "+mutationLocationList.size());

		//3. Run mutation phase
		MutationsProcessor mProcessor = new MutationsProcessor("temp", appName, mutantsFolder);

		if(multithread) {
			mProcessor.processMultithreaded(mutationLocationList, extraPath, apkName);
		} else {
			mProcessor.process(mutationLocationList, extraPath, apkName);
		}

	}

}
