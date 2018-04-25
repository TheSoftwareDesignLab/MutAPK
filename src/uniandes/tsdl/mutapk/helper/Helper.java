package uniandes.tsdl.mutapk.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Helper {

	public static Helper instance = null;
	public static String currDirectory = "";
	public final static String MANIFEST = "AndroidManifest.xml";

	public static Helper getInstance(){
		if (instance == null) {
			instance = new Helper();
		}
		return instance;
	}

	public String getCurrentDirectory() throws UnsupportedEncodingException{
		if(currDirectory.equals("")){
			String currentDirectory = APKToolWrapper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(currentDirectory,"UTF-8");
			//		decodedPath = "C:\\Users\\caev0\\Documents\\2018-10\\Tesis1\\MAPK\\";
			String os = System.getProperty("os.name").toLowerCase();
			if (os.indexOf("win")>=0 && decodedPath.matches("/.:/.*")) {
				decodedPath = decodedPath.replaceAll("/", "\\\\").substring(1);
			} else if(os.indexOf("win")<0){
				int pos = decodedPath.substring(1).indexOf("/")+1;
				decodedPath = decodedPath.replace(decodedPath.substring(0,pos), decodedPath.substring(0,pos-1).toLowerCase());
			}
			if (decodedPath.endsWith(".jar")) {
				decodedPath = decodedPath.substring(0,decodedPath.lastIndexOf("/")+1);
			}
			currDirectory = decodedPath;
		}
		return currDirectory;
	}
}
