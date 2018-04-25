package uniandes.tsdl.mutapk;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import uniandes.tsdl.mutapk.detectors.MutationLocationDetector;
import uniandes.tsdl.mutapk.detectors.MutationLocationListBuilder;
import uniandes.tsdl.mutapk.helper.APKToolWrapper;
import uniandes.tsdl.mutapk.model.MutationType;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.OperatorBundle;
import uniandes.tsdl.mutapk.processors.MutationsProcessor;
import uniandes.tsdl.mutapk.processors.TextBasedDetectionsProcessor;

public class MutAPK {

	public static void main(String[] args) {
		try {
			runMutAPK(args);
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
		String libsPath = args[3];
		String operatorsDir = args[4];
		boolean multithread = Boolean.parseBoolean(args[5]);
		
		// Fix params based in OS
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			mutantsFolder = mutantsFolder.replaceAll("/", "\\\\")+"\\";
			libsPath = libsPath.replaceAll("/", "\\\\")+"\\";
			apkPath = apkPath.replaceAll("/", "\\\\");
			apkName = apkPath.substring(apkPath.lastIndexOf("\\"));
		} else {
			apkName = apkPath.substring(apkPath.lastIndexOf("/"));
		}
		// Decode the APK
		APKToolWrapper.openAPK(apkPath, libsPath);

		//Read selected operators
		OperatorBundle operatorBundle = new OperatorBundle(operatorsDir);
		System.out.println(operatorBundle.printSelectedOperators());

		//Text-Based operators selected
		List<MutationLocationDetector> textBasedDetectors = operatorBundle.getTextBasedDetectors();
		
		//1. Run detection phase for Text-based detectors
		HashMap<MutationType, List<MutationLocation>> locations = TextBasedDetectionsProcessor.process("temp", textBasedDetectors);
		
		Set<MutationType> keys = locations.keySet();
		List<MutationLocation> list = null;
		System.out.println("Mutation Operator			AmounMutants");
		for (MutationType mutationType : keys) {
			list = locations.get(mutationType);
			System.out.println(mutationType+"			"+list.size());
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
			mProcessor.processMultithreaded(mutationLocationList, libsPath, apkName);
		} else {
			mProcessor.process(mutationLocationList, libsPath, apkName);
		}
	}

}
