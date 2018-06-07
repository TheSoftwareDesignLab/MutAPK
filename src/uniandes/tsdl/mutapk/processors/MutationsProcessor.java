package uniandes.tsdl.mutapk.processors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import uniandes.tsdl.mutapk.helper.APKToolWrapper;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;
import uniandes.tsdl.mutapk.operators.MutationOperatorFactory;

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
		System.out.println("Creating folder for mutant "+ mutantIndex);
		String path = getMutantsRootFolder() + File.separator + getAppName() + "-mutant" + mutantIndex;
		System.out.println("Copying app information into mutant "+ mutantIndex+" folder");
		FileUtils.copyDirectory(new File(getAppFolder()), new File(path + File.separator + "src"));
		return path;

	}

	public void process(List<MutationLocation> locations, String extraPath, String apkName) throws IOException {
		MutationOperatorFactory factory = MutationOperatorFactory.getInstance();
		MutationOperator operator = null;
		int mutantIndex = 1;
		String mutantFolder = null;
		String newMutationPath = null;
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(getMutantsRootFolder() + File.separator + getAppName() + "-mutants.log"));
		for (MutationLocation mutationLocation : locations) {
			try {
				setupMutantFolder(mutantIndex);
				System.out.println("Mutant: " + mutantIndex + " - Type: " + mutationLocation.getType());
				operator = factory.getOperator(mutationLocation.getType().getId());
				mutantRootFolder = getMutantsRootFolder() + File.separator + getAppName() + "-mutant" + mutantIndex
						+ File.separator;
				mutantFolder = mutantRootFolder + "src" + File.separator;
				// The mutant should be written in mutantFolder

				newMutationPath = mutationLocation.getFilePath().replace(appFolder, mutantFolder);
				// System.out.println(newMutationPath);
				mutationLocation.setFilePath(newMutationPath);
				operator.performMutation(mutationLocation, writer, mutantIndex);

				APKToolWrapper.buildAPK(mutantRootFolder, extraPath, apkName, mutantIndex);

			} catch (Exception e) {
				Logger.getLogger(MutationsProcessor.class.getName())
						.warning("- Error generating mutant  " + mutantIndex);
				e.printStackTrace();
			}
			mutantIndex++;
		}
		writer.close();
	}

	public void processMultithreaded(List<MutationLocation> locations, final String extraPath, final String apkName)
			throws IOException {

		final BufferedWriter writer = new BufferedWriter(
				new FileWriter(getMutantsRootFolder() + File.separator + getAppName() + "-mutants.log"));
		final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		final List<Future<String>> results = new LinkedList<Future<String>>();

		writer.write("ThreadPool: "+Runtime.getRuntime().availableProcessors()+"\n");
		int mutantIndex = 0;

		for (final MutationLocation mutationLocation : locations) {
			mutantIndex++;
			final int currentMutationIndex = mutantIndex;
			System.out.println("Mutant: " + currentMutationIndex + " - " + mutationLocation.getType().getName());
			setupMutantFolder(currentMutationIndex);
			results.add(executor.submit(new Callable<String>() {

				public String call() {
					try {
						// Select operator
						MutationOperatorFactory factory = MutationOperatorFactory.getInstance();
						MutationOperator operator = factory.getOperator(mutationLocation.getType().getId());

						// Set up folders
						String mutantRootFolder = getMutantsRootFolder() + File.separator + getAppName() + "-mutant"
								+ currentMutationIndex + File.separator;
						String mutantFolder = mutantRootFolder + "src" + File.separator;
						String newMutationPath = mutationLocation.getFilePath().replace(appFolder, mutantFolder);
						mutationLocation.setFilePath(newMutationPath);

						// Perform mutation
						operator.performMutation(mutationLocation, writer, currentMutationIndex);
						APKToolWrapper.buildAPK(mutantRootFolder, extraPath, apkName, currentMutationIndex);
						// writer.close();

					} catch (Exception e) {
						Logger.getLogger(MutationsProcessor.class.getName())
								.warning("- Error generating mutant  " + currentMutationIndex);
						e.printStackTrace();
					}

					return "";
				}
			}));
		}

		// If more output for single operator is needed
		// FileOutputStream out = new
		// FileOutputStream(getMutantsRootFolder()+File.separator+getAppName()+"-process.log");
		// PrintStream pout = new PrintStream(out);
		// for (Future<String> result : results) {
		// try {
		// pout.print(result.get());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// pout.flush();
		// pout.close();

		executor.shutdown();
		if (executor.isTerminated()) {
			writer.close();
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
