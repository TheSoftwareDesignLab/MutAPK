package uniandes.tsdl.mutapk.operators.activity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.StringGenerator;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class InvalidLabel implements MutationOperator{

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws IOException {
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());
		String toMutate = "";
		String mutatedString = "";

		
		for(int i=0; i < lines.size(); i++){
			
			String currLine = lines.get(i);
			
			if(i == location.getLine()){
				//Apply mutation
				String sub1 = currLine.substring(0, location.getStartColumn());
				toMutate = currLine.substring(location.getStartColumn(), location.getEndColumn());
				String sub2 = currLine.substring(location.getEndColumn());

				//String mutatedString = StringMutator.performMutation(toMutate);
				mutatedString = StringGenerator.generateRandomString();
				currLine = sub1 + mutatedString + sub2;
			}
			
			newLines.add(currLine);
		}
		
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {location.getStartLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" activity label at line "+location.getLine()+" has been change from \""+toMutate+"\" to \""+mutatedString+"\"");
		writer.newLine();
		writer.flush();
		return true;
	}

}
