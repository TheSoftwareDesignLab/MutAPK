package uniandes.tsdl.mutapk.detectors.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import uniandes.tsdl.mutapk.detectors.TextBasedDetector;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.MutationType;
import uniandes.tsdl.mutapk.model.location.MutationLocation;

public class WrongStringResourceDetector extends TextBasedDetector {
	
	
	public WrongStringResourceDetector(){
		this.type = MutationType.WRONG_STRING_RESOURCE;
	}
	
	@Override
	public List<MutationLocation> analyzeApp(String rootPath) throws Exception {
		List<MutationLocation> locations = new ArrayList<MutationLocation>();
	
		String path = rootPath+File.separator+"res"+File.separator+"values"+File.separator+Helper.STRINGS;
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		int startLine = 0;
		boolean isStringTag = false;
		int currentLine = 0;
		while( (line = reader.readLine() ) != null){
	
			if( line.contains("<string ")){
				startLine = currentLine;
				isStringTag  = true;
			}
			
			if( isStringTag && line.contains("</string")){
				locations.add( MutationLocation.buildLocation(path, startLine, currentLine, -1, -1, -1, -1, this.getType()));
				isStringTag  = false;
			}
			currentLine++;
		}
		reader.close();
		return locations;
	}
}
