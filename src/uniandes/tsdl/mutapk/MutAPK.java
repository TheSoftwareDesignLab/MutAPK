package uniandes.tsdl.mutapk;

import java.io.File;
import java.io.IOException;

import uniandes.tsdl.mutapk.Helpers.APKToolWrapper;

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
		if (args.length != 4) {
			System.out.println("******* ERROR: INCORRECT USAGE *******");
			System.out.println("Argument List:");
			System.out.println("1. APK path");
			System.out.println("2. Package Name");
			System.out.println("3. Mutants path");
			System.out.println("4. Binaries path");
			//			System.out.println("1. Binaries path");
			//			System.out.println("2. App Source Code path");
			//						System.out.println("5. Directory containing the operator.properties file");
			//						System.out.println("6. Multithread generation (true/false)");
			return;
		}

		//Getting arguments
		String apkPath = args[0];
		String appName = args[1];
		String mutantsFolder = args[2];
		String libsPath = args[3];
		//		String rootPath = args[1];
		//		String operatorsDir = args[4];
		//		creates forlder for temporal steps
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			mutantsFolder = mutantsFolder+"\\";
			libsPath = libsPath+"\\";
		}
		new File("temp").mkdirs();
		APKToolWrapper.openAPK(apkPath, libsPath);
		
		System.out.println(apkPath);
	}

}
