package uniandes.tsdl.mutapk.helper;

import java.io.File;
import java.io.IOException;


public class APKToolWrapper {

	private static String MANIFEST = "AndroidManifest.xml";
	private static String MMANIFEST = "AAndroidManifest.xml";

	public static void openAPK(String path, String libsPath) throws IOException, InterruptedException{
		String decodedPath = Helper.getInstance().getCurrentDirectory();
		// Creates folder for decoded app
		new File(decodedPath+"temp").mkdirs();
		Process ps = Runtime.getRuntime().exec(new String[]{"java","-jar",decodedPath+libsPath+"apktool.jar","d",decodedPath+path,"-o",decodedPath+"temp","-f"});
		System.out.println("Trabajando con tu APK...");
		ps.waitFor();
//		InputStream es = ps.getErrorStream();
//		byte e[] = new byte[es.available()];
//		es.read(e,0,e.length);
//		System.out.println("ERROR: "+ new String(e));
//		InputStream is = ps.getInputStream();
//		byte b[] = new byte[is.available()];
//		is.read(b,0,b.length);
//		System.out.println("INFO: "+new String(b));
//		System.out.println(decodedPath);
//		Helper.getInstance().makeAppDebuggeable(decodedPath+"temp");
		System.out.println("Tu APK fue procesado exitosamente! YEI!");
	}

	public static void buildAPK(String path, String libsPath, String appName, boolean verbose) throws IOException, InterruptedException{
		String decodedPath = Helper.getInstance().getCurrentDirectory();
		Process ps = Runtime.getRuntime().exec(new String[]{"java","-jar",decodedPath+libsPath+"apktool.jar","b",decodedPath+path+File.separator+"src"+File.separator,"-o",decodedPath+path+appName,"-f"});
		if(verbose){System.out.println("Compilando...");}
		ps.waitFor();
		if(verbose){System.out.println("APK generado :)");}
		//		InputStream es = ps.getErrorStream();
		//		byte e[] = new byte[es.available()];
		//		es.read(e,0,e.length);
		//		System.out.println("ERROR: "+ new String(e));
		//		InputStream is = ps.getInputStream();
		//		byte b[] = new byte[is.available()];
		//		is.read(b,0,b.length);
		//		System.out.println("INFO: "+new String(b));
	}
}


