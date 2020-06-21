package edu.uniandes.tsdl.mutapk.processors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import edu.uniandes.tsdl.mutapk.hashfunction.sha3.ApkHashOrder;
import edu.uniandes.tsdl.mutapk.hashfunction.sha3.ApkHashSeparator;
import edu.uniandes.tsdl.mutapk.hashfunction.sha3.Sha3;
import edu.uniandes.tsdl.mutapk.helper.APKToolWrapper;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;
import edu.uniandes.tsdl.mutapk.operators.MutationOperator;
import edu.uniandes.tsdl.mutapk.operators.MutationOperatorFactory;

public class MutationsProcessor {

	private String appFolder;
	private String appName;
	private String mutantsRootFolder;
	private String mutantRootFolder;

	public MutationsProcessor(String appFolder, String appName, String mutantsRootFolder) {
		super();
		this.appFolder = appFolder;
		this.appName = appName;
		this.mutantsRootFolder = mutantsRootFolder;
	}

	private String setupMutantFolder(int mutantIndex) throws IOException {
		System.out.println("Creating folder for mutant " + mutantIndex);
		String path = getMutantsRootFolder() + File.separator + getAppName() + "-mutant" + mutantIndex;
		System.out.println("Copying app information into mutant " + mutantIndex + " folder");
		FileUtils.copyDirectory(new File(getAppFolder()), new File(path + File.separator + "src"));
		return path;

	}

	public void process(List<MutationLocation> locations, String extraPath, String apkName) 
			throws IOException, ParserConfigurationException, SAXException, InterruptedException {
		MutationOperatorFactory factory = MutationOperatorFactory.getInstance();
		MutationOperator operator = null;
		int mutantIndex = 1;
		String mutantFolder = null;
		String newMutationPath = null;
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(getMutantsRootFolder() + File.separator + getAppName() + "-mutants.log"));
		BufferedWriter wwriter = new BufferedWriter(
				new FileWriter(getMutantsRootFolder() + File.separator + getAppName() + "-times.csv"));
		wwriter.write("mutantIndex;mutantType;copyingTime;mutationTime;buildingTime;isEqu;isDup;dupID;itCompiles");
		wwriter.newLine();
		wwriter.flush();
		for (MutationLocation mutationLocation : locations) {
			Long copyingIni = System.currentTimeMillis();
			setupMutantFolder(mutantIndex);
			Long copyingEnd = System.currentTimeMillis();
			Long copyingTime = copyingEnd - copyingIni;
			wwriter.write(mutantIndex + ";" + mutationLocation.getType().getId() + ";" + copyingTime + ";0;0;0;0;-1;0");
			wwriter.newLine();
			wwriter.flush();
			Long mutationIni = System.currentTimeMillis();
			System.out.println("Mutant: " + mutantIndex + " - Type: " + mutationLocation.getType());
			operator = factory.getOperator(mutationLocation.getType().getId());

			mutantRootFolder = getMutantsRootFolder() + getAppName() + "-mutant" + mutantIndex
					+ File.separator;
			mutantFolder = mutantRootFolder + "src" + File.separator;
			// Create mutation
			newMutationPath = mutationLocation.getFilePath().replace(appFolder, mutantFolder);
			// System.out.println(newMutationPath);
			mutationLocation.setFilePath(newMutationPath);
			try {
				operator.performMutation(mutationLocation, writer, mutantIndex);
				Long mutationEnd = System.currentTimeMillis();
				Long mutationTime = mutationEnd - mutationIni;
				
				// Verify id the mutant is a duplicate
				verifyDuplicateMutants(extraPath, apkName, mutantIndex, mutantFolder, newMutationPath, wwriter,
						mutationLocation, mutationEnd, mutationTime);
				mutantIndex++;
			} catch (Exception e) {
				wwriter.write(mutantIndex + ";" + mutationLocation.getType().getId() + ";0;0;0;0;1;0;-1");
				wwriter.newLine();
				wwriter.flush();
				
				System.out.println(e.getMessage());
			} 
				
		}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("The maximum id is : " + ApkHashOrder.getInstance().getId());
		System.out.println("The length of hasmap is: " + ApkHashOrder.getInstance().getLength());
		System.out.println("------------------------------------------------------------------------------------");
		writer.close();
		wwriter.close();
	}

	private void verifyDuplicateMutants(String extraPath, String apkName, int mutantIndex, String mutantFolder,
			String newMutationPath, BufferedWriter wwriter, MutationLocation mutationLocation, Long mutationEnd,
			Long mutationTime) throws FileNotFoundException, IOException, InterruptedException {
		File manifest = new File(mutantFolder + File.separator + "AndroidManifest.xml");
		File smali = new File(mutantFolder + File.separator + "smali");
		File resource = new File(mutantFolder + File.separator + "res");
		ApkHashSeparator apkHashSeparator = this.generateApkHashSeparator(manifest, smali, resource, mutantIndex);

		ApkHashSeparator apkHashSeparatorDuplicate = ApkHashOrder.getInstance()
				.setApkHashSeparator(apkHashSeparator);
		if (apkHashSeparatorDuplicate != null) {
			int compare = apkHashSeparatorDuplicate.getMutantId();
			if(compare == 0) {
				System.out.println("The mutant with id: "+apkHashSeparator.getMutantId()+" is equivalent.");
				wwriter.write(mutantIndex + ";" + mutationLocation.getType().getId() + ";0;" + mutationTime + ";" + -1 + ";1;0;"+compare+";0");
			} else {
				System.out.println("The mutant with id: "+apkHashSeparator.getMutantId()+" is duplicated with mutant with id: "+compare);
				wwriter.write(mutantIndex + ";" + mutationLocation.getType().getId() + ";0;" + mutationTime + ";" + -1 + ";0;1;"+compare+";0");
			}
			wwriter.newLine();
			wwriter.flush();
		} else {
			generateMutant(extraPath, apkName, mutantIndex, mutantFolder, newMutationPath, wwriter,
					mutationLocation, mutationEnd, mutationTime);
		}
	}

	private void generateMutant(String extraPath, String apkName, int mutantIndex, String mutantFolder,
			String newMutationPath, BufferedWriter wwriter, MutationLocation mutationLocation, Long mutationEnd,
			Long mutationTime) throws IOException, InterruptedException {
		mutantRootFolder = getMutantsRootFolder() + getAppName() + "-mutant" + mutantIndex
				+ File.separator;
		mutantFolder = mutantRootFolder + "src" + File.separator;
		boolean result = APKToolWrapper.buildAPK(mutantRootFolder, extraPath, apkName, mutantIndex);
		File mutatedFile = new File(newMutationPath);
		mutantRootFolder = getMutantsRootFolder() + getAppName() + "-mutant" + mutantIndex
				+ File.separator;
		mutantFolder = mutantRootFolder + "src" + File.separator;
		String fileName = (new File(newMutationPath)).getName();
		File mutantRootFolderDir = new File(mutantRootFolder + fileName);
		FileUtils.copyFile(mutatedFile, mutantRootFolderDir);
		File srcFolder = new File(mutantFolder);
		if (result) {
			FileUtils.deleteDirectory(srcFolder);
		}
		Long buildEnd = System.currentTimeMillis();
		Long buildingTime = buildEnd - mutationEnd;
		wwriter.write(mutantIndex + ";" + mutationLocation.getType().getId() + ";0;" + mutationTime + ";"
				+ buildingTime + ";0;0;-1;" + (result?"1":"0"));
		wwriter.newLine();
		wwriter.flush();
	}

	public ApkHashSeparator generateApkHashSeparator(File manifest, File smali, File resource, int mutanteId)
			throws FileNotFoundException, IOException {
		String hashManifest = Sha3.sha512FileSeparte(manifest);
		String hashSmaliConSeperado = Sha3.sha512FileSeparte(smali);
		String hashResourceConSeperado = Sha3.sha512FileSeparte(resource);
		ApkHashSeparator apkHashSeparator = new ApkHashSeparator.Builder(hashManifest, hashSmaliConSeperado, hashResourceConSeperado, mutanteId).build();
		return apkHashSeparator;
	}

	public void processMultithreaded(List<MutationLocation> locations, final String extraPath, final String apkName)
			throws IOException, NullPointerException {

		final BufferedWriter writer = new BufferedWriter(
				new FileWriter(getMutantsRootFolder() + File.separator + getAppName() + "-mutants.log"));
		final BufferedWriter wwriter = new BufferedWriter(
				new FileWriter(getMutantsRootFolder() + File.separator + getAppName() + "-times.csv"));
		wwriter.write("mutantIndex;mutantType;copyingTime;mutationTime;buildingTime;isEqu;isDup;dupID;itCompiles");
		wwriter.newLine();
		wwriter.flush();
		final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		final List<Future<String>> results = new LinkedList<Future<String>>();

		writer.write("ThreadPool: " + Runtime.getRuntime().availableProcessors() + "\n");
		int mutantIndex = 0;

		for (final MutationLocation mutationLocation : locations) {
			mutantIndex++;
			final int currentMutationIndex = mutantIndex;
			Long copyingIni = System.currentTimeMillis();
			System.out.println("Mutant: " + currentMutationIndex + " - " + mutationLocation.getType().getName());
			setupMutantFolder(currentMutationIndex);
			Long copyingEnd = System.currentTimeMillis();
			Long copyingTime = copyingEnd - copyingIni;
			wwriter.write(currentMutationIndex + ";" + mutationLocation.getType().getId() + ";" + copyingTime + ";0;0;0;0;-1;0");
			wwriter.newLine();
			wwriter.flush();
			results.add(executor.submit(new Callable<String>() {

				public String call() throws NullPointerException, Exception {
					// Select operator
					Long mutationIni = System.currentTimeMillis();
					MutationOperatorFactory factory = MutationOperatorFactory.getInstance();
					MutationOperator operator = factory.getOperator(mutationLocation.getType().getId());

					// Set up folders
					String mutantRootFolder = getMutantsRootFolder() + File.separator + getAppName() + "-mutant"
							+ currentMutationIndex + File.separator;
					String mutantFolder = mutantRootFolder + "src" + File.separator;
					String newMutationPath = mutationLocation.getFilePath().replace(appFolder, mutantFolder);
					mutationLocation.setFilePath(newMutationPath);

					try {
						operator.performMutation(mutationLocation, writer, currentMutationIndex);
						Long mutationEnd = System.currentTimeMillis();
						Long mutationTime = mutationEnd - mutationIni;
						
						// Verify id the mutant is a duplicate
						verifyDuplicateMutants(extraPath, apkName, currentMutationIndex, mutantFolder, newMutationPath, wwriter,
								mutationLocation, mutationEnd, mutationTime);
					} catch (InterruptedException | IOException | ParserConfigurationException | SAXException e) {
						wwriter.write(currentMutationIndex + ";" + mutationLocation.getType().getId() + ";0;0;0;0;1;0;-1");
						wwriter.newLine();
						wwriter.flush();
						
						System.out.println(e.getMessage());
					}

					return "";
				}
			}));
		}

		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("The length of hasmap is: " + ApkHashOrder.getInstance().getLength());
		System.out.println("------------------------------------------------------------------------------------");

		executor.shutdown();
		if (executor.isTerminated()) {
			writer.close();
			wwriter.close();
		}
	}

	public String getAppFolder() {
		return appFolder;
	}

	public void setAppFolder(String appFolder) {
		this.appFolder = appFolder;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getMutantsRootFolder() {
		return mutantsRootFolder;
	}

	public void setMutantsRootFolder(String mutantsRootFolder) {
		this.mutantsRootFolder = mutantsRootFolder;
	}

}
