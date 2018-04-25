package uniandes.tsdl.mutapk.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import uniandes.tsdl.mutapk.model.location.MutationLocation;


public class APKToolWrapper {

	private static String MANIFEST = "AndroidManifest.xml";
	private static String MMANIFEST = "AAndroidManifest.xml";

	public static void openAPK(String path, String libsPath) throws IOException, InterruptedException{
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
//		makeAppDebuggeable(decodedPath+"temp");
		System.out.println("Tu APK fue procesado exitosamente! YEI!");
	}

	private static void makeAppDebuggeable(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(path+File.separator+MANIFEST)));
		BufferedWriter writter = new BufferedWriter(new FileWriter(new File(path+File.separator+MMANIFEST)));
		String line = null;
		boolean applicationTagFound = false;
		boolean tagAdded = false;
		while( (line = reader.readLine() ) != null){
			if((!applicationTagFound && !line.contains("<application"))|| tagAdded){
				writter.write(line);
				writter.newLine();
			} else if( line.contains("<application")){
				applicationTagFound  = true;
				if(line.contains("android:debuggeable")){
					line = line.replaceAll("android:debuggeable=[true|false]", "android:debuggeable=true");
					writter.write(line);
					writter.newLine();
					tagAdded=true;
				}
			} else if(applicationTagFound && !(line.contains(">"))){
				if(line.contains("android:debuggeable")){
					line = line.replaceAll("android:debuggeable=[true|false]", "android:debuggeable=true");
					writter.write(line);
					writter.newLine();
					tagAdded=true;
				}
			} 
			if(!tagAdded && applicationTagFound && line.contains(">")){
				line = line.replaceAll(">", " android:debuggeable=\"true\" >");
				writter.write(line);
				writter.newLine();
				tagAdded=true;
			}
		}
		writter.close();
		reader.close();
	}

	public static void buildAPK(String path, String libsPath, String appName, boolean verbose) throws IOException, InterruptedException{
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


