package uniandes.tsdl.mutapk.Helpers;

import java.io.IOException;
import java.io.InputStream;	
import java.net.URLDecoder;


public class APKToolWrapper {
	
	public static void openAPK(String path, String libsPath) throws IOException, InterruptedException{
		String currentDirectory = APKToolWrapper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(currentDirectory,"UTF-8");
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win")>=0 && decodedPath.matches("/.:/.*")) {
			decodedPath = decodedPath.replaceAll("/", "\\\\").substring(1);
		} else {
			int pos = decodedPath.substring(1).indexOf("/")+1;
			decodedPath = decodedPath.replace(decodedPath.substring(0,pos), decodedPath.substring(0,pos-1).toLowerCase());
		}
		if (decodedPath.endsWith(".jar")) {
			decodedPath = decodedPath.substring(0,decodedPath.lastIndexOf("/")+1);
		}
		Process ps = Runtime.getRuntime().exec(new String[]{"java","-jar",decodedPath+libsPath+"apktool.jar","d",decodedPath+path,"-o",decodedPath+"temp","-f"});
		System.out.println("Trabajando con tu APK...");
		ps.waitFor();
		InputStream es = ps.getErrorStream();
		byte e[] = new byte[es.available()];
		es.read(e,0,e.length);
		System.out.println("ERROR: "+ new String(e));
		InputStream is = ps.getInputStream();
		byte b[] = new byte[is.available()];
		is.read(b,0,b.length);
		System.out.println("INFO: "+new String(b));
	}
}


