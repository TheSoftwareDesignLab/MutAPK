package uniandes.tsdl.mutapk.operators.activity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.antlr.runtime.tree.CommonTree;
import org.xml.sax.SAXException;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.StringGenerator;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class DifferentActivityIntentDefinition  implements MutationOperator{

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws IOException, ParserConfigurationException, SAXException {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		Helper h = Helper.getInstance();
		List<String> activities = h.getActivities();
		CommonTree parent = (CommonTree) mLocation.getTree().getParent();
		List hijos = (List<CommonTree>)parent.getChildren();
		boolean founded = false;
		String toMutate = "";
		String mutatedString = "";
		int realLine = 0;
		for (int i = mLocation.getTree().getChildIndex(); i > 0 && !founded; i--) {
			CommonTree temp = (CommonTree) hijos.get(i);
			List<CommonTree> hijoss = (List<CommonTree>) temp.getChildren();
			if(temp.getType()==142 
					&& hijoss.get(0).getText().equals("const-class")){
				founded =true;
				realLine = temp.getLine();
				toMutate = hijoss.get(2).getText();
				String activityName =activities.get((int)(Math.random()*activities.size()));
				if(!activityName.startsWith(Helper.getPackageName())) {
					activityName=Helper.getPackageName()+"."+((activityName.startsWith("."))?activityName.substring(1):activityName);
				}
				activityName = activityName.replace(".", "/");
				mutatedString = "L"+activityName+";";
				while(mutatedString.equals(toMutate)){
					activityName =activities.get((int)(Math.random()*activities.size()));
					if(!activityName.startsWith(Helper.getPackageName())) {
						activityName=Helper.getPackageName()+"."+((activityName.startsWith("."))?activityName.substring(1):activityName);
					}
					activityName = activityName.replace(".", "/");
					mutatedString = "L"+activityName+";";
				}
			}
		}
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		
		for(int i=0; i < lines.size(); i++){
			
			String currLine = lines.get(i);
			
			if(i == realLine-1){
				//Apply mutation
				
				int startColumn = currLine.indexOf(toMutate);
				String sub1 = currLine.substring(0, startColumn);
				currLine = sub1 + mutatedString;
			}
			
			newLines.add(currLine);
		}
		
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {realLine}, writer);
		writer.write("	For mutant "+mutantIndex+" component name at line "+realLine+" has been change from \""+toMutate+"\" to \""+mutatedString+"\"");
		writer.newLine();
		writer.flush();
		return true;
	}

}





























