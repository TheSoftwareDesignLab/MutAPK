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
		List hijos = parent.getChildren();
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
				mutatedString = "L"+activities.get((int)Math.random()*activities.size()).replace(".", "/")+";";
				while(mutatedString.equals(toMutate)){
					mutatedString = "L"+activities.get((int)Math.random()*activities.size()).replace(".", "/")+";";
				}
			}
		}
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		
		for(int i=0; i < lines.size(); i++){
			
			String currLine = lines.get(i);
			
			if(i == realLine){
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
		Helper.mutationSucces(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), location.getStartLine(), writer);
		writer.write("	For mutant "+mutantIndex+" activity label at line "+location.getLine()+" has been change from \""+toMutate+"\" to \""+mutatedString+"\"");
		writer.newLine();
		writer.flush();
		return true;
	}

}
