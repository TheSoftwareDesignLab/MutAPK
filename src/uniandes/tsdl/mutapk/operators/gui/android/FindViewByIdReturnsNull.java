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
		int prevLine = parent.getParent().getChild(parent.getChildIndex()-1).getLine();
		int lastLine = parent.getParent().getChild(parent.getChildIndex()+2).getLine();

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
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {prevLine, mLocation.getLine(), mLocation.getLine()+2, lastLine}, writer);
		writer.write("	For mutant "+mutantIndex+" the element retrieved at line "+location.getStartLine()+" has been set to null");
		writer.newLine();
		writer.flush();
		return true;
	}

}
