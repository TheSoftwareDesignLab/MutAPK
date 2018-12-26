package uniandes.tsdl.mutapk.detectors.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.tsdl.mutapk.detectors.TextBasedDetector;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.MutationType;
import uniandes.tsdl.mutapk.model.location.InvalidColorMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;

public class InvalidColorDetector extends TextBasedDetector {

	public InvalidColorDetector(){
		this.type = MutationType.INVALID_COLOR;
	}

	@Override
	public List<MutationLocation> analyzeApp(String rootPath) throws Exception {
		List<MutationLocation> locations = new ArrayList<MutationLocation>();
		List<Entry<String,String>> colorHex = new ArrayList<Entry<String,String>>();


		String path = rootPath + File.separator+"res"+File.separator+"values"+File.separator + Helper.COLORS;

		File fXmlFile = new File(path);
		if(fXmlFile.exists()) { 
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			// Get all activities
			NodeList nodeList = doc.getElementsByTagName("color");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String colorName = node.getAttributes().getNamedItem("name").getNodeValue();
				colorHex.add(new SimpleEntry<String,String>(colorName,node.getTextContent()));
			}
			for(Entry<String,String> color : colorHex){
				System.out.println(color.getKey());
				System.out.println(color.getValue());
			}

			// Read Source Code
			BufferedReader reader = new BufferedReader(new FileReader(
					new File(path)));
			String line = null;
			int startLine = 0;
			int startCol = 0;
			int endCol = 0;
			int lineNum = 0;
			String originalColor = "";
			String colorName = "";
			boolean isColorTag = false;
			int currentLine = 0;
			while ((line = reader.readLine()) != null) {

				if (line.contains("<color")) {
					startLine = currentLine;
					isColorTag = true;
				}
				if (isColorTag) {
					for(Entry<String,String> color : colorHex){
						if(line.contains("\""+color.getKey()+"\"")){
							startCol = line.indexOf(color.getValue())+1;
							endCol = startCol+color.getValue().length()-1;
							lineNum = currentLine;
							originalColor = color.getValue();
							colorName = color.getKey();
						}
					}
					if (line.contains(">")) {
						locations.add(InvalidColorMutationLocation.buildLocation(path, startLine, currentLine, startCol, endCol, lineNum, -1, this.getType(), colorName, originalColor));
						isColorTag = false;
					}
				}

				currentLine++;
			}
			reader.close();
		}
		return locations;
	}

}
