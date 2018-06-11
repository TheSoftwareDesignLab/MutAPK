package uniandes.tsdl.mutapk.detectors.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.tsdl.mutapk.detectors.TextBasedDetector;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.MutationType;
import uniandes.tsdl.mutapk.model.location.MutationLocation;

public class SDKVersionDetector extends TextBasedDetector {

	public SDKVersionDetector() {
		this.type = MutationType.SDK_VERSION;
	}

	@Override
	public List<MutationLocation> analyzeApp(String rootPath) throws Exception {
		List<MutationLocation> locations = new ArrayList<MutationLocation>();
		List<String> sdkVersion = new ArrayList<String>();


		String path = rootPath + File.separator + Helper.MANIFEST;

		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		// Get all activities
		NodeList nodeList = doc.getElementsByTagName("uses-sdk");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			NamedNodeMap attrMap = node.getAttributes();
			// Loop through attributes
			for (int j = 0; j < attrMap.getLength(); j++) {
				String nodeName = attrMap.item(j).getNodeName();
				// Get all sdk version
				if(nodeName.equals(Helper.MIN_SDK_VERSION) || 
						nodeName.equals(Helper.TARGET_SDK_VERSION) ||
						nodeName.equals(Helper.MAX_SDK_VERSION)){

					sdkVersion.add(nodeName);
				}
			}
		}

		// Read Source Code
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		int startCol = 0;
		int endCol = 0;
		int lineNum = 0; 

		boolean isActivityTag = false;
		int currentLine = 0;
		while ((line = reader.readLine()) != null) {

			if (line.contains("<uses-sdk")) {
				isActivityTag = true;
			}
			if (isActivityTag) {
				for(String version : sdkVersion){
					if(line.contains(version)){
						int startSDK = line.indexOf(version) + version.length();
						startCol = line.indexOf("\"", startSDK)+1;
						endCol = line.indexOf("\"", startCol);
						lineNum = currentLine;
						locations.add(MutationLocation.buildLocation(path, lineNum, -1, startCol, endCol, lineNum, -1, this.getType()));
					}
				}
				if (line.contains(">")) {
					isActivityTag = false;
				}
			}

			currentLine++;
		}
		reader.close();

		return locations;
	}
}
