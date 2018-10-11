package uniandes.tsdl.mutapk.operators.gui.android;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.ASTHelper;
import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class ViewComponentNotVisible implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {

		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree parent = (CommonTree) mLocation.getTree().getParent();
		List<CommonTree> hijos = (List<CommonTree>)parent.getChildren();
		String constVarName = hijos.get(1).getChild(1).getText();
		CommonTree selTree = ASTHelper.hasIPutAndIGet(mLocation.getTree());
		int selLine = selTree.getLine();
		String className = selTree.getChild(3).toString();
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < selLine; i++){
			newLines.add(lines.get(i));
		}
		
		//Apply mutation
		newLines.add("");
		newLines.add("    const/4 v9, 0x4");
		newLines.add("");
		newLines.add("    invoke-virtual {"+constVarName+", v9}, "+className+"->setVisibility(I)V");
		newLines.add("");
		
		for(int i=selLine; i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}

		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {selLine}, writer);
		writer.write("	For mutant "+mutantIndex+" the visibility of element retrieved at line "+location.getStartLine()+" has been set to INVISIBLE at lines: "+(selLine+2)+" and "+(selLine+4));
		writer.newLine();
		writer.flush();
		return true;
	}

}
