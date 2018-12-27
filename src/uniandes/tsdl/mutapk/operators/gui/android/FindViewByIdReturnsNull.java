package uniandes.tsdl.mutapk.operators.gui.android;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.antlr.smaliParser;
import uniandes.tsdl.mutapk.helper.ASTHelper;
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
		CommonTree cast = ASTHelper.getFirstBrotherNamedOfType(smaliParser.I_STATEMENT_FORMAT21c_TYPE, "check-cast", parent);
		String varName = "";
		int position = -1;
		if(cast!=null && (cast.getLine()-parent.getLine()<5)) {
			varName = cast.getChild(1).toString();
			position = cast.getLine();
		} else {
			CommonTree moveResultObject = ASTHelper.getFirstBrotherNamedOfType(smaliParser.I_STATEMENT_FORMAT11x, "move-result-object", parent);
			varName = moveResultObject.getChild(1).toString();
			position = moveResultObject.getLine();
		}
		

		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < parent.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
		
		//Apply mutation
		newLines.add("");
		newLines.add("    const/4 "+varName+", 0x0");
		newLines.add("");
		
		for(int i=position; i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}

		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {mLocation.getLine(), position}, writer);
		writer.write("	For mutant "+mutantIndex+" the element retrieved at line "+location.getStartLine()+" has been set to null");
		writer.newLine();
		writer.flush();
		return true;
	}

}
