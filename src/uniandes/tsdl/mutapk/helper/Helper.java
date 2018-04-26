package uniandes.tsdl.mutapk.helper;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Helper {

	public static Helper instance = null;
	public static String currDirectory = "";
	public static List<String> actNames = new ArrayList<String>();
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

	public List<String> getActivities() throws ParserConfigurationException, SAXException, IOException{
		if(actNames.isEmpty()){
			List<String> activityNames = new ArrayList<String>();
			File fXmlFile = new File(getCurrentDirectory()+"temp"+File.separator+MANIFEST);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// Get all activities
			NodeList nodeList = doc.getElementsByTagName("activity");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				NamedNodeMap attrMap = node.getAttributes();
				// Loop through attributes
				for (int j = 0; j < attrMap.getLength(); j++) {
					if (attrMap.item(j).getNodeName().equals("android:name")) {
						// Get all activity names
						activityNames.add(attrMap.item(j).getNodeValue());
					}
				}
			}
			actNames = activityNames;
		}
		return actNames;
	}
}
