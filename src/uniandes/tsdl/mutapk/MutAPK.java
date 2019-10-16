package uniandes.tsdl.mutapk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import uniandes.tsdl.mutapk.helper.Helper;
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

		} catch (NumberFormatException e) {
			System.out.println("Amount of mutants parameter is not a number!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void runMutAPK(String[] args) throws NumberFormatException, Exception {
		//Usage Error
		if (args.length < 6) {
			System.out.println("******* ERROR: INCORRECT USAGE *******");
			System.out.println("Argument List:");
			System.out.println("1. APK path");
			System.out.println("2. Package Name");
			System.out.println("3. Mutants path");
			System.out.println("4. Binaries path");
			System.out.println("5. Directory containing the operator.properties file");
			System.out.println("6. Multithread generation (true/false)");
			System.out.println("7. OPTIONAL Amount of mutants");
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
		int amountMutants = -1;
		if(args.length>6) {
			amountMutants = Integer.parseInt(args[6]);
		}


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
		//Read selected operators
		OperatorBundle operatorBundle = new OperatorBundle(operatorsDir);
		System.out.println(operatorBundle.printSelectedOperators());

		if(amountMutants>0 && operatorBundle.getAmountOfSelectedOperators()>amountMutants) {
			throw new Exception("You must select as many mutants as selected operators, right now you select "+operatorBundle.getAmountOfSelectedOperators()+" operators but only ask for "+amountMutants+" mutants");
		}

		Helper.getInstance();
		Helper.setPackageName(appName);
		// Decode the APK
		APKToolWrapper.openAPK(apkPath, extraPath);


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
		printLocationList(mutationLocationList, mutantsFolder, appName);
		System.out.println("Total Locations: "+mutationLocationList.size());

		if(amountMutants>0) {
			System.out.println("We have found: "+mutationLocationList.size()+" possible mutation points, we are going to select "+amountMutants+" of those");
			mutationLocationList = selectMutants(amountMutants, locations);
			System.out.println(mutationLocationList.size());
		}

		//3. Run mutation phase
		MutationsProcessor mProcessor = new MutationsProcessor("temp", appName, mutantsFolder);

		if(multithread) {
			mProcessor.processMultithreaded(mutationLocationList, extraPath, apkName);
		} else {
			mProcessor.process(mutationLocationList, extraPath, apkName);
		}

	}

	private static List<MutationLocation> selectMutants(int amountMutants, HashMap<MutationType, List<MutationLocation>> locations) {

		HashMap<MutationType, List<MutationLocation>> newLocations = new HashMap<MutationType, List<MutationLocation>>();
		HashMap<MutationType, List<MutationLocation>> tempLocations = locations;
		int newAmountMutants = amountMutants;

		for(MutationType key : tempLocations.keySet()) {
			if(tempLocations.get(key).size()>0) {
				int selectedMutantNumber = (int)Math.random()*tempLocations.get(key).size();
				MutationLocation selectedMutant =  tempLocations.get(key).get(selectedMutantNumber);
				ArrayList temp = new ArrayList<MutationLocation>();
				temp.add(selectedMutant);
				newLocations.put(key, temp);
				tempLocations.get(key).remove(selectedMutantNumber);
				newAmountMutants--;
			}
		}
		List<MutationLocation> mutationLocationList = MutationLocationListBuilder.buildList(tempLocations);
		List<MutationLocation> newMutationLocationList = MutationLocationListBuilder.buildList(newLocations);

		for (int i = 0; i < newAmountMutants; i++) {
			int selectedMutantNumber = (int)Math.random()*mutationLocationList.size();
			MutationLocation selectedMutant =  mutationLocationList.get(selectedMutantNumber);
			newMutationLocationList.add(selectedMutant);
			mutationLocationList.remove(selectedMutantNumber);
		}
		return newMutationLocationList;
	}

	private static void printLocationList(List<MutationLocation> mutationLocationList, String mutantsFolder, String appName) {

		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter(mutantsFolder + File.separator + appName + "-locations.json"));
			writer.write("{");
			writer.newLine();
			writer.flush();
			for (int i = 0; i < mutationLocationList.size(); i++) {
				MutationLocation temp = mutationLocationList.get(i);
				writer.write("	\""+(i+1)+"\":{");
				writer.newLine();
				writer.write("		\"mutationTypeID\":\""+temp.getType().getId()+"\",");
				writer.newLine();
				writer.write("		\"mutationTypeName\":\""+temp.getType().getName()+"\",");
				writer.newLine();
				writer.write("		\"filePath\":\""+temp.getFilePath()+"\",");
				writer.newLine();
				writer.write("		\"line\":\""+temp.getLine()+"\",");
				writer.newLine();
				writer.write("		\"startLine\":\""+temp.getStartLine()+"\",");
				writer.newLine();
				writer.write("		\"endLine\":\""+temp.getEndLine()+"\",");
				writer.newLine();
				writer.write("		\"startColumn\":\""+temp.getStartColumn()+"\",");
				writer.newLine();
				writer.write("		\"endColumn\":\""+temp.getEndColumn()+"\",");
				writer.newLine();
				writer.write("		\"length\":\""+temp.getLength()+"\"");
				writer.newLine();
				writer.write((i==mutationLocationList.size()-1)?"	}":"	},");
				writer.newLine();
				writer.flush();
			}
			writer.write("}");
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
