package uniandes.tsdl.mutapk.operators.activity;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.IntegerGenerator;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class WrongMainActivity  implements MutationOperator{

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception{
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());
		String mainActivity = Helper.getInstance().getMainActivity();
		String otherActivity = Helper.getInstance().getMainActivity();
		List<String> activities = Helper.getInstance().getActivities();
		int otherActivityIndex = 0;
		while(otherActivity.equals(mainActivity)){
			otherActivity = activities.get(IntegerGenerator.generateRandomInt(0, activities.size()));
		}
		for(int i=0; i < lines.size(); i++){
			String currLine = lines.get(i);
			if(currLine.contains(otherActivity)){
				otherActivityIndex = i;
				currLine = currLine.replaceAll(otherActivity, mainActivity);
			} else if (currLine.contains(mainActivity)){
				currLine = currLine.replaceAll(mainActivity, otherActivity);
			}
			newLines.add(currLine);
		}
		
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {location.getStartLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" activity \""+mainActivity+"\" at line "+location.getStartLine()+" has been replaced as main activity by \""+otherActivity+"\" at line "+(otherActivityIndex+1));
		writer.newLine();
		writer.flush();
		
		return true;
	}

}
