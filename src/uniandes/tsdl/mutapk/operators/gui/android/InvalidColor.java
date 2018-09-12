package uniandes.tsdl.mutapk.operators.gui.android;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.HexadecimalGenerator;
import uniandes.tsdl.mutapk.model.location.InvalidColorMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class InvalidColor implements MutationOperator{

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {
		
		InvalidColorMutationLocation mLocation = (InvalidColorMutationLocation) location;
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(mLocation.getFilePath());
		String newColor = "";
		
		for(int i=0; i < lines.size(); i++){
			
			String currLine = lines.get(i);
			
			if(i == mLocation.getLine()){
				//Apply mutation
				String sub1 = currLine.substring(0, mLocation.getStartColumn());
				String sub2 = currLine.substring(mLocation.getEndColumn());

				newColor = HexadecimalGenerator.generateRandomHexa();
				currLine = sub1 + newColor + sub2;
			}
			
			newLines.add(currLine);
		}
		
		FileHelper.writeLines(mLocation.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, mLocation.getFilePath(), mLocation.getType().getName(), new int[] {mLocation.getStartLine()}, writer);
		
		writer.write("	For mutant "+mutantIndex+" the value of the color named \""+mLocation.getColorName()+"\" at line "+(location.getStartLine()+1)+" has been changed from \""+mLocation.getOriginalColor()+"\" to \"#"+newColor+"\"");
		writer.newLine();
		writer.flush();
		
		return true;
	}

}
