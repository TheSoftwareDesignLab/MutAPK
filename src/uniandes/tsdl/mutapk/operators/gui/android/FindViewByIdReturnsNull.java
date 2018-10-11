package uniandes.tsdl.mutapk.operators.gui.android;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class FindViewByIdReturnsNull implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {

		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree parent = (CommonTree) mLocation.getTree().getParent();
		List<CommonTree> hijos = (List<CommonTree>)parent.getChildren();
		String constVarName = hijos.get(1).getChild(1).getText();
		List<CommonTree> uncles = (List<CommonTree>)((CommonTree)parent.getParent()).getChildren();
		boolean founded = false;
		int prevLine = -1;
		for (int i = parent.getChildIndex()-1; i > 0 && !founded; i--) {
			CommonTree tempUncle = uncles.get(i);
			if(tempUncle.getType()==156 && tempUncle.getChild(1).toString().equals(constVarName)) {
				founded = true;
				prevLine = tempUncle.getLine();
			}
		}
		boolean foundedd = false;
		boolean casted = false;
		int firstLastLine = -1;
		int lastLine = -1;
		for(int i = parent.getChildIndex()+1; i < parent.getChildIndex()+5 && (!foundedd || !casted); i++) {
			CommonTree tempUncle = uncles.get(i);
			System.out.println(tempUncle.toStringTree());
			if(tempUncle.getType()==136 && tempUncle.getChild(0).toString().equals("move-result-object")) {
				foundedd = true;
				firstLastLine = tempUncle.getLine();
				lastLine = tempUncle.getLine();
			} else if (foundedd && tempUncle.getType()==142 && tempUncle.getChild(0).toString().equals("check-cast")) {
				casted = true;
				lastLine = tempUncle.getLine();
			}
			System.out.println(foundedd+" "+casted);
		}

		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < prevLine-1; i++){
			newLines.add(lines.get(i));
		}
		
		//Apply mutation
		newLines.add("");
		newLines.add("    const/4 "+constVarName+", 0x0");
		newLines.add("");
		
		for(int i=lastLine; i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}

		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {prevLine, mLocation.getLine(), firstLastLine, lastLine}, writer);
		writer.write("	For mutant "+mutantIndex+" the element retrieved at line "+location.getStartLine()+" has been set to null");
		writer.newLine();
		writer.flush();
		return true;
	}

}
