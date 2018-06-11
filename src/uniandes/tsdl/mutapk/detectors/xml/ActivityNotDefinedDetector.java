package uniandes.tsdl.mutapk.detectors.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import uniandes.tsdl.mutapk.detectors.TextBasedDetector;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.MutationType;
import uniandes.tsdl.mutapk.model.location.MutationLocation;

public class ActivityNotDefinedDetector extends TextBasedDetector {

	public ActivityNotDefinedDetector(){
		this.type = MutationType.ACTIVITY_NOT_DEFINED;
	}
	
	@Override
	public List<MutationLocation> analyzeApp(String rootPath) throws Exception {
		Stack<String> stack = new Stack<>();
		List<MutationLocation> locations = new ArrayList<MutationLocation>();
	
		String path = rootPath+File.separator+Helper.MANIFEST;
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		int startLine = 0;
		

		boolean isActivityTag = false;
		int currentLine = 0;
		while( (line = reader.readLine() ) != null){
	
			if( line.contains("<activity")){
				startLine = currentLine;
				isActivityTag  = true;
				stack.add("activity");

			}else if(isActivityTag && line.contains("<") && !line.contains("</") ){
				stack.add("other");
			}
			
			if(isActivityTag && (line.contains("</") || line.contains("/>") || line.contains("-->"))){
				stack.pop();
			}
			
			if(isActivityTag && stack.isEmpty()){
				locations.add(MutationLocation.buildLocation(path, startLine, currentLine,-1,-1,-1,-1,this.getType()));
				isActivityTag  = false;
			}
			
			currentLine++;
		}
		reader.close();
		
		return locations;
	}
}
