package uniandes.tsdl.mutapk.operators.activity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class NullIntent implements MutationOperator{

	@SuppressWarnings("unchecked")
	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws IOException {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree parent = (CommonTree) mLocation.getTree().getParent();
		List<CommonTree> hijos = (List<CommonTree>)parent.getChildren();
		int intentInstanceLine = -1;
		String intentInstanceLineVar = mLocation.getTree().getChild(1).getChild(0).toString();
		int contextInstanceLine = -1;
		String contextInstanceLineVar = mLocation.getTree().getChild(1).getChild(1).toString();
		int classComponentLine = -1;
		String classComponentLineVar = mLocation.getTree().getChild(1).getChild(2).toString();
		int actualChild = mLocation.getTree().getChildIndex();
		while(actualChild>0 && (intentInstanceLine == -1 || contextInstanceLine == -1 || classComponentLine == -1)) {
			CommonTree temp = (CommonTree) hijos.get(actualChild);
			List<CommonTree> hijoss = (List<CommonTree>) temp.getChildren();
			if(temp.getType()==142 
					&& hijoss.get(0).getText().equals("const-class")
					&& hijoss.get(1).getText().equals(classComponentLineVar)){
				classComponentLine = temp.getLine();
			} else if(temp.getType()==142 
					&& hijoss.get(0).getText().equals("new-instance")
					&& hijoss.get(1).getText().equals(intentInstanceLineVar)){
				intentInstanceLine = temp.getLine();
			} else if(temp.getType()==148
					&& hijoss.get(0).getText().equals("iget-object")
					&& hijoss.get(1).getText().equals(contextInstanceLineVar)) {
				contextInstanceLine = temp.getLine();
			}
			
			actualChild--;
		}
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());
		
		
		//Add lines before the MutationLocation
		for(int i=0; i < intentInstanceLine-1; i++){
			newLines.add(lines.get(i));
		}
		
		
		//Apply mutation
		newLines.add("    const/4 "+intentInstanceLineVar+", 0x0");
//		String linesToConsider = "";
//		boolean newLineFlag = false;
//		for(int i = location.getStartLine(); i <= location.getEndLine(); i++){
//			if(newLineFlag){
//				linesToConsider += " "+lines.get(i);
//			} else {
//				linesToConsider += lines.get(i);
//				newLineFlag = true;
//			}
//		}
//		
//		String sub1 = linesToConsider.substring(0, location.getStartColumn());
//		String sub2 = linesToConsider.substring(location.getEndColumn());
//		
//		String mutatedLine = sub1 + "null"  + sub2;
//		newLines.add(mutatedLine);
		
		
		//Add lines after the MutationLocation
		for(int i=location.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}
		
		int[] mutatedlines = new int[]{intentInstanceLine, contextInstanceLine, classComponentLine, mLocation.getLine()};
		String mutatedLines = "{ ";
		for (int i = 0; i < mutatedlines.length; i++) {
			mutatedLines += mutatedlines[i]+", ";
		}
		mutatedLines = mutatedLines.substring(0,mutatedLines.length()-2)+" }";
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), mutatedlines, writer);
		writer.write("	For mutant "+mutantIndex+" intent defined at lines "+mutatedLines+" has been defined as null");
		writer.newLine();
		writer.flush();
		return true;
	}

}
