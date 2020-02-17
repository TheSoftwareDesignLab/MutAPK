package edu.uniandes.tsdl.mutapk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.uniandes.tsdl.antlr.smaliParser;
import edu.uniandes.tsdl.jflex.smaliFlexLexer;
import edu.uniandes.tsdl.mutapk.detectors.MutationLocationDetector;
import edu.uniandes.tsdl.mutapk.detectors.MutationLocationListBuilder;
import edu.uniandes.tsdl.mutapk.helper.APKToolWrapper;
import edu.uniandes.tsdl.mutapk.helper.Helper;
import edu.uniandes.tsdl.mutapk.model.MutationType;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;
import edu.uniandes.tsdl.mutapk.operators.OperatorBundle;
import edu.uniandes.tsdl.mutapk.processors.MutationsProcessor;
import edu.uniandes.tsdl.mutapk.processors.SourceCodeProcessor;
import edu.uniandes.tsdl.mutapk.processors.TextBasedDetectionsProcessor;
import edu.uniandes.tsdl.mutapk.selector.ConfidenceIntervalSelector;
import edu.uniandes.tsdl.mutapk.selector.SelectorConfidenceInterval;
import edu.uniandes.tsdl.mutapk.selector.SelectorType;
import edu.uniandes.tsdl.smali.LexerErrorInterface;

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
		// Usage Error
//		if (args.length < 6) {
//			System.out.println("******* ERROR: INCORRECT USAGE *******");
//			System.out.println("Argument List:");
//			System.out.println("1. APK path");
//			System.out.println("2. Package Name");
//			System.out.println("3. Mutants path");
//			System.out.println("4. Binaries path");
//			System.out.println("5. Directory containing the operator.properties file");
//			System.out.println("6. Multithread generation (true/false)");
//			System.out.println("7. isCIALL (true/false)");
//			System.out.println("8. Value Confidence Level");
//			System.out.println("9. Value Margin error ");
//			return;
//		}

		// Getting arguments
		String apkName;

		String apkPath = "";
		String appName = "";
		String mutantsFolder = "";
		String extraPath = "";
		String operatorsDir = "";
		boolean multithread = true;
		String selectorType = "";

		// Optional arguments
		int amountMutants = -1;

		boolean isCIIndividual = false;
		int confidenceLevel = -1;
		int marginError = -1;

		String apkOldPath = "";
		String apkNewPath = "";

		JSONParser parser = new JSONParser();
		try {
			Object obj = parser
					.parse(new FileReader(args[0]));
			// A JSON object. Key value pairs are unordered. JSONObject supports
			// java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
			apkPath = ".\\" + (String) jsonObject.get("APKPath");
			appName = ".\\" + (String) jsonObject.get("PackageName");
			mutantsFolder = ".\\" + (String) jsonObject.get("MutanPath");
			extraPath = ".\\" + (String) jsonObject.get("BinariesPath");
			operatorsDir = ".\\" + (String) jsonObject.get("DirectoryOfOperator.properties");
			multithread = Boolean.valueOf((String) jsonObject.get("MultithreadGeneration"));
			selectorType = (String) jsonObject.get("SelectorType");

			if (!"ALL".equals(selectorType)) {
				if ("AmountMutants".equals(selectorType)) {
					amountMutants = Integer.parseInt((String) jsonObject.get("AmountMutants"));
				} else if ("ConfidenceLevel".equals(selectorType)) {
					JSONObject jsonConfidenceLevelObject = (JSONObject) jsonObject.get("ConfidenceLevel");
					isCIIndividual = Boolean.valueOf((String) jsonConfidenceLevelObject.get("Individual"));
					confidenceLevel = Integer.parseInt((String) jsonConfidenceLevelObject.get("ConfidenceLevel"));
					marginError = Integer.parseInt((String) jsonConfidenceLevelObject.get("MarginError"));
				} else if ("APKVersions".equals(selectorType)) {
					JSONObject jsonAPKVersionsObject = (JSONObject) jsonObject.get("APKVersions");
					apkOldPath = (String) jsonAPKVersionsObject.get("OldAPKPath");
					apkNewPath = (String) jsonAPKVersionsObject.get("NewAPKPath");
				}
			}

		} catch (ArrayIndexOutOfBoundsException aib) {
			System.out.println("Please write a path to the json file");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Nombre		Valor");
		System.out.println("apkPath: " + apkPath);
		System.out.println("appName: " + appName);
		System.out.println("mutantsFolder: " + mutantsFolder);
		System.out.println("extraPath: " + extraPath);
		System.out.println("operatorsDir: " + operatorsDir);
		System.out.println("multithread: " + multithread);
		System.out.println("selectorType: " + selectorType);
		System.out.println("amountMutants: " + amountMutants);
		System.out.println("isCIIndividual: " + isCIIndividual);
		System.out.println("confidenceLevel: " + confidenceLevel);
		System.out.println("marginError: " + marginError);
		System.out.println("apkOldPath: " + apkOldPath);
		System.out.println("apkNewPath: " + apkNewPath);


		// Fix params based in OS
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			mutantsFolder = mutantsFolder.replaceAll("/", File.separator) + File.separator;
			extraPath = extraPath.replaceAll("/", File.separator) + File.separator;
			apkPath = apkPath.replaceAll("/", File.separator);
			apkName = apkPath.substring(apkPath.lastIndexOf("\\"));
		} else {
			apkName = apkPath.substring(apkPath.lastIndexOf("/"));
		}
		// Read selected operators
		OperatorBundle operatorBundle = new OperatorBundle(operatorsDir);
		System.out.println(operatorBundle.printSelectedOperators());

//		if(amountmutants>0 && operatorbundle.getamountofselectedoperators()>amountmutants) {
//			throw new exception("you must select as many mutants as selected operators, right now you select "+operatorbundle.getamountofselectedoperators()+" operators but only ask for "+amountmutants+" mutants");
//		}

		Helper.getInstance();
		Helper.setPackageName(appName);
		// Decode the APK
		APKToolWrapper.openAPK(apkPath, extraPath);

		// Text-Based operators selected
		List<MutationLocationDetector> textBasedDetectors = operatorBundle.getTextBasedDetectors();

		// 1. Run detection phase for Text-based detectors
		HashMap<MutationType, List<MutationLocation>> locations = TextBasedDetectionsProcessor.process("temp",
				textBasedDetectors);

		// //2. Run detection phase for AST-based detectors
		// //2.1 Preprocessing: Find locations to target API calls (including calls to
		// constructors)
		// //SourceCodeProcessor scp = SourceCodeProcessor.getInstance(); (not safe, if
		// MPlus is executed on different apps)
		SourceCodeProcessor scp = new SourceCodeProcessor(operatorBundle);
		locations.putAll(scp.processFolder("temp", extraPath, appName));

		// //2.2. Call the detectors on each location in order to find any extra
		// information required for each case.
		// locations = scp.findExtraInfoRequired(locations);

		Set<MutationType> keys = locations.keySet();
		List<MutationLocation> list = null;
		int totalMutants = 0;
		System.out.println("Amount Mutants	Mutation Operator");
		for (MutationType mutationType : keys) {
			list = locations.get(mutationType);
			totalMutants += list.size();
			System.out.println(list.size() + "		" + mutationType);
			// for (MutationLocation mutationLocation : list) {
			// System.out.println("File: "+mutationLocation.getFilePath()+", start line:" +
			// mutationLocation.getStartLine()+", end line:
			// "+mutationLocation.getEndLine()+", start
			// column"+mutationLocation.getStartColumn());
			// }
		}

		// 3. Build MutationLocation List
		List<MutationLocation> mutationLocationList = MutationLocationListBuilder.buildList(locations);
		printLocationList(mutationLocationList, mutantsFolder, appName);
		System.out.println("Total Locations: " + mutationLocationList.size());

		// 4. Select Selector
		if (!"ALL".equals(selectorType)) {
			if ("AmountMutants".equals(selectorType)) {
				if (amountMutants > 0) {
					System.out.println("Amount of mutants");
					mutationLocationList = selectMutants(amountMutants, locations);
				} else {
					throw new IllegalArgumentException("Amount Mutants should be greater than 0 ");
				}
			} else if ("ConfidenceLevel".equals(selectorType)) {
				if (isCIIndividual) {
					System.out.println("Type of selector: Confidence Level Individual");
				} else {
					System.out.println("Type of selector: Confidence Level General");
				}
				System.out.println("Confidencel Level: " + confidenceLevel + " Margin Error: " + marginError);
				SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false,
						totalMutants, isCIIndividual, confidenceLevel, marginError);
				ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
				mutationLocationList = CIMS.mutantSelector(locations, selectorConfidenceInterval);

			} else if ("APKVersions".equals(selectorType)) {
				//TODO
				throw new UnsupportedOperationException("The " + selectorType + " is not implemented, yet");
			} else {
				throw new UnsupportedOperationException("The " + selectorType + " is not recognized, the operators available are: ALL, ConfidenceLevel and APKVersions");
			}
		}

		// 3. Run mutation phase
		MutationsProcessor mProcessor = new MutationsProcessor("temp", appName, mutantsFolder);

		if (multithread) {
			mProcessor.processMultithreaded(mutationLocationList, extraPath, apkName);
		} else {
			mProcessor.process(mutationLocationList, extraPath, apkName);
		}

	}

	static List<MutationLocation> selectMutants(int amountMutants,
			HashMap<MutationType, List<MutationLocation>> locations) {

		HashMap<MutationType, List<MutationLocation>> newLocations = new HashMap<MutationType, List<MutationLocation>>();
		HashMap<MutationType, List<MutationLocation>> tempLocations = locations;
		int newAmountMutants = amountMutants;

		for (MutationType key : tempLocations.keySet()) {
			if (tempLocations.get(key).size() > 0) {
				int selectedMutantNumber = (int) Math.random() * tempLocations.get(key).size();
				MutationLocation selectedMutant = tempLocations.get(key).get(selectedMutantNumber);
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
			int selectedMutantNumber = (int) Math.random() * mutationLocationList.size();
			MutationLocation selectedMutant = mutationLocationList.get(selectedMutantNumber);
			newMutationLocationList.add(selectedMutant);
			mutationLocationList.remove(selectedMutantNumber);
		}
		return newMutationLocationList;
	}

	private static void printLocationList(List<MutationLocation> mutationLocationList, String mutantsFolder,
			String appName) {

		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter( mutantsFolder + File.separator + appName + "-locations.json"));
			writer.write("{");
			writer.newLine();
			writer.flush();
			for (int i = 0; i < mutationLocationList.size(); i++) {
				MutationLocation temp = mutationLocationList.get(i);
				writer.write("	\"" + (i + 1) + "\":{");
				writer.newLine();
				writer.write("		\"mutationTypeID\":\"" + temp.getType().getId() + "\",");
				writer.newLine();
				writer.write("		\"mutationTypeName\":\"" + temp.getType().getName() + "\",");
				writer.newLine();
				writer.write("		\"filePath\":\"" + temp.getFilePath() + "\",");
				writer.newLine();
				writer.write("		\"line\":\"" + temp.getLine() + "\",");
				writer.newLine();
				writer.write("		\"startLine\":\"" + temp.getStartLine() + "\",");
				writer.newLine();
				writer.write("		\"endLine\":\"" + temp.getEndLine() + "\",");
				writer.newLine();
				writer.write("		\"startColumn\":\"" + temp.getStartColumn() + "\",");
				writer.newLine();
				writer.write("		\"endColumn\":\"" + temp.getEndColumn() + "\",");
				writer.newLine();
				writer.write("		\"length\":\"" + temp.getLength() + "\"");
				writer.newLine();
				writer.write((i == mutationLocationList.size() - 1) ? "	}" : "	},");
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
