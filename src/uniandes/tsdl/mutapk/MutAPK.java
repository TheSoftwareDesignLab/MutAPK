package uniandes.tsdl.mutapk;

import java.io.IOException;

public class MutAPK {

	public static void main(String[] args) {
		try {
			runMutAPK(args);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void runMutAPK(String[] args) throws IOException{
		//Usage Error
		if(args.length != 1){
			System.out.println("******* ERROR: INCORRECT USAGE *******");
			System.out.println("Argument List:");
			System.out.println("1. APK path");
			//			System.out.println("2. App Source Code path");
			//			System.out.println("3. App Name");
			//			System.out.println("4. Mutants path");
			//			System.out.println("5. Directory containing the operator.properties file");
			//			System.out.println("6. Multithread generation (true/false)");
			return;
		}

		//Getting arguments
		String apkPath = args[0];
		//		String rootPath = args[1];
		//		String appName = args[2];
		//		String mutantsFolder = args[3];
		//		String operatorsDir = args[4];
		//		boolean multithread = Boolean.parseBoolean(args[5]);
		
		System.out.println(apkPath);
	}

}
