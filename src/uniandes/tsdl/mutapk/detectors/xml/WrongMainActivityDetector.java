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

public class WrongMainActivityDetector extends TextBasedDetector {

	public WrongMainActivityDetector() {
		this.type = MutationType.WRONG_MAIN_ACTIVITY;
	}

	@Override
	public List<MutationLocation> analyzeApp(String rootPath) throws Exception {
		List<MutationLocation> locations = new ArrayList<MutationLocation>();
		String mainActivity = Helper.getInstance().getMainActivity();

		String path = rootPath + File.separator + Helper.MANIFEST;

		//Main activity not found!
		if(mainActivity.equals("")){
			return locations;
		}

		// Read Source Code
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = null;
		int startLine = 0;
		int startCol = 0;
		int endCol = 0;
		int lineNum = 0;

		boolean isActivityTag = false;
		boolean isMainActivity = false;
		int currentLine = 1;
		while ((line = reader.readLine()) != null) {

			if (line.contains("<activity")) {
				startLine = currentLine;
				isActivityTag = true;
			}
			if (isActivityTag) {
				if(line.contains("android:name") && line.contains(mainActivity)){
					startCol = line.indexOf("\""+mainActivity)+1;
					endCol = startCol+mainActivity.length();
					isMainActivity = true;
					lineNum = currentLine;
				}
				if (line.contains(">")) {
					if(isMainActivity){
						locations.add(MutationLocation.buildLocation(path, startLine, currentLine, startCol, endCol, lineNum, -1, this.getType()));
						isMainActivity=false;
					}
					isActivityTag = false;
				}
			}

			currentLine++;
		}
		reader.close();

		return locations;
	}

}
