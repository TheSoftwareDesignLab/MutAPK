package uniandes.tsdl.mutapk.operators.programming.android;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.StringGenerator;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class WrongStringResource implements MutationOperator{

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {

		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < location.getStartLine(); i++){
			newLines.add(lines.get(i));
		}

		//Apply mutation
		String linesToConsider = "";
		boolean newLineFlag = false;
		for(int i = location.getStartLine(); i <= location.getEndLine(); i++){
			if(newLineFlag){
				linesToConsider += " "+lines.get(i);
			} else {
				linesToConsider += lines.get(i);
				newLineFlag = true;
			}
		}
		
		int startS = linesToConsider.indexOf("<string");
		
		if(startS < 0){
			return false;
		}
		
		int start = linesToConsider.indexOf(">", startS)+1;
		
		int end = linesToConsider.indexOf("</string>");
		
		String sub1 = linesToConsider.substring(0, start);
		String toMutate = linesToConsider.substring(start,end);
		String sub2 = linesToConsider.substring(end);
		int strNameStart = linesToConsider.indexOf("name=\"")+6;
		int strNameEnd = linesToConsider.indexOf("\"",strNameStart);
		String stringName = linesToConsider.substring(strNameStart,strNameEnd);

		String randomString = StringGenerator.generateRandomString();
		
		String mutatedLine = sub1 + randomString + sub2;
		newLines.add(mutatedLine);


		//Add lines after the MutationLocation
		for(int i=location.getEndLine()+1; i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}


		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {location.getStartLine()}, writer);
		
		writer.write("	For mutant "+mutantIndex+" the value of the string named \""+stringName+"\" at line "+(location.getStartLine()+1)+" has been changed from \""+toMutate+"\" to \""+randomString+"\"");
		writer.newLine();
		writer.flush();

		return true;
	}
	
}
