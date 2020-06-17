package edu.uniandes.tsdl.mutapk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.antlr.runtime.RecognitionException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import edu.uniandes.tsdl.mutapk.detectors.MutationLocationDetector;
import edu.uniandes.tsdl.mutapk.detectors.MutationLocationListBuilder;
import edu.uniandes.tsdl.mutapk.exception.MutAPKException;
import edu.uniandes.tsdl.mutapk.hashfunction.sha3.ApkHashOrder;
import edu.uniandes.tsdl.mutapk.hashfunction.sha3.ApkHashSeparator;
import edu.uniandes.tsdl.mutapk.helper.APKToolWrapper;
import edu.uniandes.tsdl.mutapk.helper.ASTHelper;
import edu.uniandes.tsdl.mutapk.helper.CallGraphHelper;
import edu.uniandes.tsdl.mutapk.helper.Helper;
import edu.uniandes.tsdl.mutapk.model.CallGraphNode;
import edu.uniandes.tsdl.mutapk.model.MutationType;
import edu.uniandes.tsdl.mutapk.model.SmaliAST;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;
import edu.uniandes.tsdl.mutapk.operators.OperatorBundle;
import edu.uniandes.tsdl.mutapk.processors.MutationsProcessor;
import edu.uniandes.tsdl.mutapk.processors.SourceCodeProcessor;
import edu.uniandes.tsdl.mutapk.processors.TextBasedDetectionsProcessor;
import edu.uniandes.tsdl.mutapk.selector.SelectorConfidenceIntervalMethod;
import edu.uniandes.tsdl.mutapk.selector.SelectorAmountMutants;
import edu.uniandes.tsdl.mutapk.selector.SelectorAmountMutantsMethod;
import edu.uniandes.tsdl.mutapk.selector.SelectorConfidenceInterval;

public class MutAPK {

	// Constants for Selection Strategies
	private static final String ALL_MUTANTS_SS = "all";
	private static final String AMOUNT_MUTANTS_SS = "amountMutants";
	private static final String REPRESENTATIVE_SUBSET_SS = "representativeSubset";

	// Getting arguments
	static String apkName;

	static String apkPath = "";
	static String appName = "";
	static String mutantsFolder = "";
	static String extraPath = "";
	static String operatorsDir = "";
	static boolean multithread = true;
	static boolean ignoreDeadCode = true;
	static String selectionStrategy = "";

	// Optional arguments
	static int amountMutants = -1;

	static boolean isRSPerOPerator = false;
	static int confidenceLevel = -1;
	static int marginError = -1;

	static OperatorBundle operatorBundle;	

	static HashMap<String, SmaliAST> smaliASTs = new HashMap<String, SmaliAST>();

	static HashMap<String, HashMap<String, CallGraphNode>> callGraph = new HashMap<String, HashMap<String, CallGraphNode>>();

	static HashMap<String, HashMap<String, CallGraphNode>> deadCode = new HashMap<String, HashMap<String, CallGraphNode>>();

	public static void main(String[] args) {
		try {
			// long initialTime = System.currentTimeMillis();
			// System.out.println(initialTime);
			runMutAPK(args);
			// long finalTime = System.currentTimeMillis();
			// System.out.println(finalTime);
			// System.out.println(finalTime-initialTime);
		} catch (MutAPKException | NumberFormatException | IOException | ParseException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close code snippet from console
			System.out.println("```");
		}
	}

	public static void runMutAPK(String[] args) throws NumberFormatException, FileNotFoundException, IOException, ParseException, MutAPKException, InterruptedException, ParserConfigurationException, SAXException, RecognitionException {

		// Read JSON config file
		readConfig(args[0]);

		// Read selected operators
		operatorBundle = new OperatorBundle(operatorsDir);
		System.out.println("## Selected Mutation Operators:\n");
		System.out.println(operatorBundle.printSelectedOperators());

		// Check correctness of parameters' values and preprocess values to fit OS path format 		
		checkAndPrepareParameters();

		Helper.getInstance();
		Helper.setPackageName(appName);

		// Decode the APK
		String apkAbsolutePath = APKToolWrapper.openAPK(apkPath, extraPath);
		System.out.println("--------------------------------------");


		//--------------------------------------------
		// Run detection phase for AST-based detectors
		//--------------------------------------------
		// Generate HashMap with all the ASTs
		SourceCodeProcessor scp = new SourceCodeProcessor();
		scp.generateASTsMap(extraPath, appName, smaliASTs);
		if(ignoreDeadCode) {

			Long initTime = System.currentTimeMillis();

			// Generate the Call Graph
			System.out.println("## Call Graph Results");
			System.out.println("");
			System.out.println("Method Type			| Amount");
			System.out.println("----------------|---------");

			callGraph = CallGraphHelper.getCallGraph(smaliASTs);
			int cNDC = 0;
			int cDC = 0;

			for(Entry<String, HashMap<String, CallGraphNode>> entry : callGraph.entrySet()) {
				for(Entry<String, CallGraphNode> entryy : entry.getValue().entrySet()) {
					CallGraphNode cGN = entryy.getValue();
					boolean isDeadCode = methodIsDeadCode(cGN);
					if(!isDeadCode) {
						cNDC++;
					} else {
						if(deadCode.get(cGN.getUnitName())!=null) {
							deadCode.get(cGN.getUnitName()).put(cGN.getId(), cGN);
						} else {
							HashMap<String, CallGraphNode> temp = new HashMap<String, CallGraphNode>();
							temp.put(cGN.getId(), cGN);
							deadCode.put(cGN.getUnitName(), temp);
						}
						cDC++;
					}
				}
			}
			System.out.println("CalledAndDefined			| "+cNDC);
			System.out.println("DeadCode			| "+cDC);
			System.out.println("\n----------------------------------");

			// Check Dead Code Methods

//			for(Entry<String, HashMap<String, CallGraphNode>> entry : deadCode.entrySet()) {
//				System.out.println(entry.getKey());
//				for(Entry<String, CallGraphNode> entryy : entry.getValue().entrySet()) {
//					System.out.println("	"+entryy.getKey());
//				}
//			}

			// Prune ASTs
			for(Entry<String, SmaliAST> entry: smaliASTs.entrySet()) {
				if(deadCode.get(entry.getKey())!=null) {
					smaliASTs.put(entry.getKey(), ASTHelper.pruneAST(entry.getValue(),deadCode.get(entry.getKey())));				
				}
			}

			Long duration = (System.currentTimeMillis()-initTime);
			System.out.println("");
			System.out.println("> It took "+duration+" miliseconds to remove dead code from APK analysis.");


		}

		// Text-Based operators selected
		List<MutationLocationDetector> textBasedDetectors = operatorBundle.getTextBasedDetectors();

		// Run detection phase for Text-based detectors
		HashMap<MutationType, List<MutationLocation>> locations = TextBasedDetectionsProcessor.process("temp", textBasedDetectors);

		// Generate PFP over pruned ASTs
		for(Entry<String, SmaliAST> entry : smaliASTs.entrySet()) {
			SmaliAST temp = entry.getValue();

			HashMap<MutationType, List<MutationLocation>> fileLocations = ASTHelper.findLocations(temp, operatorBundle);
			appendLocations(fileLocations, locations);
		}

		// Report the statistics of the found Potential Fault Locations
		Set<MutationType> keys = locations.keySet();
		List<MutationLocation> list = null;
		int totalMutants = 0;
		System.out.println("## Amount of Potential Fault Locations per Mutation Operator\n");
		System.out.println("Amount Mutants	| Mutation Operator");
		System.out.println("----------------|---------------------");
		for (MutationType mutationType : keys) {
			list = locations.get(mutationType);
			totalMutants += list.size();
			System.out.println(list.size() + "		| " + mutationType);
		}

		// Check if the amount of PFLocations is lower than the requested by the user
		if(totalMutants < amountMutants) {
			throw new MutAPKException("The total of mutants need to be greater than the amount of mutants asked");
		}
		System.out.println("");

		// Build MutationLocation List
		List<MutationLocation> mutationLocationList = MutationLocationListBuilder.buildList(locations);
		printLocationList(mutationLocationList, mutantsFolder, appName);
		System.out.println("\nTotal Locations: " + mutationLocationList.size());
		System.out.println();
		System.out.println("--------------------------------------");

		// Select Selector
		switch (selectionStrategy) {
		case AMOUNT_MUTANTS_SS:
			SelectorAmountMutantsMethod selectorAmountMutantsMethod = new SelectorAmountMutantsMethod();
			SelectorAmountMutants selectorAmountMutants = new SelectorAmountMutants(false, false, totalMutants,
					amountMutants);
			mutationLocationList = selectorAmountMutantsMethod.mutantSelector(locations, selectorAmountMutants);
			break;
		case REPRESENTATIVE_SUBSET_SS:
			SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false,
					totalMutants, isRSPerOPerator, confidenceLevel, marginError);
			SelectorConfidenceIntervalMethod CIMS = new SelectorConfidenceIntervalMethod();
			mutationLocationList = CIMS.mutantSelector(locations, selectorConfidenceInterval);
			break;
		default:
			break;
		}
		System.out.println("");

		System.out.println("## Mutation Process Log");
		System.out.println();
		System.out.println("```sh");

		// Execute mutation phase
		MutationsProcessor mProcessor = new MutationsProcessor("temp", appName, mutantsFolder);

		// Create de apkhash for the base folder
		File manifest = new File(apkAbsolutePath + File.separator + "AndroidManifest.xml");
		File smali = new File(apkAbsolutePath + File.separator + "smali");
		File resource = new File(apkAbsolutePath + File.separator + "res");


		// Create ApkHashSeparator
		ApkHashSeparator apkHashSeparator = mProcessor.generateApkHashSeparator(manifest, smali, resource, 0);
		// Add the base apkHashSeparator
		ApkHashOrder.getInstance().setApkHashSeparator(apkHashSeparator);

		if (multithread) {
			mProcessor.processMultithreaded(mutationLocationList, extraPath, apkName);
		} else {
			mProcessor.process(mutationLocationList, extraPath, apkName);
		}

	}

	private static boolean methodIsDeadCode(CallGraphNode cGN) {

		String[] exceptions = new String[] {
				"<init>","<clinit>","init","onClick","onCreate","onOptionsItemSelected","onCreateOptionsMenu","onResume","update",
				"query","getType","insert","doInBackground","onPostExecute","delete","clone","onCreateDialog","onCancel","onRestoreInstanceState",
				"onSaveInstanceState","onRetainNonConfigurationInstance","run","getParent","onNothingSelected","onItemSelected","onProgressUpdate",
				"onPreExecute", "onReceive","toString","onItemClick","getUri","onPreferenceClick","onTabChanged","getDropDownView","getCount",
				"getViewTypeCount","getView","registerDataSetObserver","saved","isEnabled","onActivityResult","unregisterDataSetObserver","attach",
				"onChange","isIntentAvailable","onDateChanged","onContextItemSelected", "onCreateContextMenu"
		};

		if(cGN.getCallers().size()>0) {
			return false;
		}

		for (int i = 0; i < exceptions.length; i++) {
			if(cGN.getId().startsWith(exceptions[i]+"(")) {
				return false;
			}
		}
		return true;

	}

	private static void appendLocations(HashMap<MutationType, List<MutationLocation>> source, HashMap<MutationType, List<MutationLocation>> target){

		for(Entry<MutationType, List<MutationLocation>> entry : source.entrySet()){
			List<MutationLocation> sourceLocations = source.get(entry.getKey());
			List<MutationLocation> targetLocations = target.get(entry.getKey());

			if(targetLocations != null){
				targetLocations.addAll(sourceLocations);
			} else {
				targetLocations = sourceLocations;
			}

			target.put(entry.getKey(), targetLocations);
		}

	}

	private static void readConfig(String configFilePath) throws NumberFormatException, FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(configFilePath));
			JSONObject jsonObject = (JSONObject) obj;
			apkPath = getVariableValuesString(jsonObject, "apkPath");
			appName = getVariableValuesString(jsonObject, "appName");
			mutantsFolder = getVariableValuesString(jsonObject, "mutantsFolder");
			multithread = Boolean.valueOf(getVariableValuesString(jsonObject, "multithreadExec"));
			ignoreDeadCode = Boolean.valueOf(getOptionalVariableValuesString(jsonObject, "ignoreDeadCode"));
			selectionStrategy = getVariableValues(jsonObject, "selectionStrategy");
			operatorsDir = getVariableValuesString(jsonObject, "operatorsDir");
			extraPath = getVariableValuesString(jsonObject, "extraPath");

			// Impreme valor por defecto
			System.out.println("");
			System.out.println("## Parameters provided via config file:");
			System.out.println("");
			System.out.println("Name			| Value");
			System.out.println("------------------------|---------");
			System.out.println("apkPath 		| " + apkPath);
			System.out.println("appName 		| " + appName);
			System.out.println("mutantsFolder 		| " + mutantsFolder);
			System.out.println("extraPath 		| " + extraPath);
			System.out.println("operatorsDir 		| " + operatorsDir);
			System.out.println("multithread 		| " + multithread);
			System.out.println("ignoreDeadCode 		| " + ignoreDeadCode);
			System.out.println("selectionStrategy 	| " + selectionStrategy);

			JSONObject selectionParameters = (JSONObject) jsonObject.get("selectionParameters");
			switch (selectionStrategy) {
			case ALL_MUTANTS_SS:
				break;
			case AMOUNT_MUTANTS_SS:
				amountMutants = Integer.parseInt(getVariableValues(selectionParameters, "amountMutants"));
				System.out.println("amountMutants 		| " + amountMutants);				
				break;
			case REPRESENTATIVE_SUBSET_SS:
				isRSPerOPerator = Boolean.valueOf(getVariableValues(selectionParameters, "perOperator"));
				confidenceLevel = Integer.parseInt(getVariableValues(selectionParameters, "confidenceLevel"));
				marginError = Integer.parseInt(getVariableValues(selectionParameters, "marginError"));
				System.out.println("isRSPerOPerator 	| " + isRSPerOPerator);
				System.out.println("confidenceLevel 	| " + confidenceLevel);
				System.out.println("marginError 		| " + marginError);				
				break;
			default:
				throw new UnsupportedOperationException("The " + selectionStrategy
						+ " selection strategy is not recognized, the operators available are: all, amountMutants and representativeSubset ");//and APKVersions");
			}
			System.out.println("----------------------------------");
			System.out.println();
		} catch (NumberFormatException e) {
			throw new NumberFormatException(" \"amountMutants\" parameter' value is not a number!");
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("The file with path " + configFilePath + " can not be found");
		} catch (IOException e) {
			throw new IOException(" \"amountMutants\" parameter' value is not a number!");
		} catch (ParseException e) {
			throw e;
		}
	}

	private static void checkAndPrepareParameters() throws MutAPKException, IOException {

		// Preprocess paths to fit to OS filsesystem format
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			mutantsFolder = mutantsFolder.replaceFirst("/", File.separator+File.separator) +File.separator;
			apkPath = apkPath.replaceAll("/", File.separator+File.separator);
			apkName = apkPath.substring(apkPath.lastIndexOf("\\"));

			extraPath = extraPath.equals("")?"":extraPath.replaceAll("/", File.separator+File.separator) + File.separator;
			operatorsDir = operatorsDir.equals("")?"":operatorsDir.replaceAll("/", File.separator+File.separator) + File.separator;
		} else {
			apkName = apkPath.substring(apkPath.lastIndexOf("/"));
		}

		// Check general parameters

		String decodedPath = Helper.getInstance().getCurrentDirectory();
		// apkPath exists
		if(!(new File(Paths.get(decodedPath, apkPath).toAbsolutePath().toString())).exists()){
			throw new MutAPKException("Path to APK is not correct. The path does not exist.");
		}
		// mutantsfolder exists (in case it does not exist to create it) and to identify if it is an absolute or a relative path
		File mutantsFolderFO = new File(Paths.get(decodedPath, mutantsFolder).toAbsolutePath().toString()); 
		if(!mutantsFolderFO.exists()){
			mutantsFolderFO.mkdirs();
		}

		// operatorsDir if the folder exists, check if exists a .properties file
		File operFolderFO = new File(Paths.get(decodedPath, operatorsDir).toAbsolutePath().toString()); 
		if(!operFolderFO.exists()){
			throw new MutAPKException("Path to operator file is not correct. The path does not exist.");
		}
		System.out.println(operFolderFO.getCanonicalPath().toString());
		File[] properties = operFolderFO.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("operators.properties");
			}
		});
		if(properties.length==0) {
			throw new MutAPKException("Path to operators config file is not correct. No operators.properties file exist in folder");
		}
		// extraPath if it is a parameter of JSON, if the folder exists and if it is an absolute or relative path
		File extraFolderFO = new File(Paths.get(decodedPath, extraPath).toAbsolutePath().toString()); 
		if(!extraFolderFO.exists()){
			throw new MutAPKException("Path to extra folder is not correct. The path does not exist.");
		}
		File[] extraFO = extraFolderFO.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return ( name.endsWith("apktool.jar") || name.endsWith("uber-apk-signer.jar") || name.endsWith("materialistic.jks"));
			}
		});
		if(extraFO.length<3) {
			throw new MutAPKException("Path to extra folder is not correct. No valid extra folder was provided.");
		}

		// Check specific selection strategy parameters
		switch (selectionStrategy) {
		case AMOUNT_MUTANTS_SS:
			if (amountMutants<=0) {
				throw new MutAPKException("The \"amountMutants\" parameter must be greater than 0.");				
			}
			if (operatorBundle.getAmountOfSelectedOperators() > amountMutants) {
				throw new MutAPKException("You must request at least as many mutants as selected operators, right now you have selected "
						+ operatorBundle.getAmountOfSelectedOperators() + " operators but only have asked for " + amountMutants
						+ " mutants");
			}
			break;
		case REPRESENTATIVE_SUBSET_SS:
			if (confidenceLevel > 100 || confidenceLevel < 0) {
				throw new MutAPKException("The \"confidenceLevel\" parameter must be between 0 and 100");
			}
			// TODO What are the max value of a marginError ?
			if (marginError<0) {
				throw new MutAPKException("The \"marginError\" parameter must be greater then 0");
			}
		default:
			break;
		}
	}

	private static String getVariableValues(JSONObject jsonObject, String name) {
		String temp = (String) jsonObject.get(name);
		if (temp != null) {
			return temp;
		} else {
			throw new IllegalArgumentException("It's neccesary a value for the variable with name: " + name);
		}
	}

	private static String getOptionalVariableValuesString(JSONObject jsonObject, String name) {
		String temp = (String) jsonObject.get(name);
		if (temp != null) {
			return temp;
		} else {
			return "true";
		}
	}

	private static String getVariableValuesString(JSONObject jsonObject, String name) {
		String temp = (String) jsonObject.get(name);
		if (temp != null && !temp.equals("") && !temp.equals(" ")) {
			return temp;
		} 
		else {
			throw new IllegalArgumentException("It's neccesary a path for the " + name);
		}
	}

	private static void printLocationList(List<MutationLocation> mutationLocationList, String mutantsFolder,
			String appName) throws IOException {

		BufferedWriter writer = new BufferedWriter(
				new FileWriter(mutantsFolder + File.separator + appName + "-locations.json"));
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
	}

}
