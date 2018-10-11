package uniandes.tsdl.mutapk.operators.gui.android;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.antlr.smaliParser;
import uniandes.tsdl.mutapk.helper.ASTHelper;
import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.HexadecimalGenerator;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class InvalidViewFocus implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree parent = (CommonTree) mLocation.getTree().getParent();
		CommonTree iput = ASTHelper.getFirstBrotherNamedOfType(smaliParser.I_STATEMENT_FORMAT22c_FIELD, "iput-object", parent);
		String objectClass = iput.getChild(5).getText();
		String varName = iput.getChild(1).getText();
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < iput.getLine(); i++){
			newLines.add(lines.get(i));
		}
		
//		//Apply mutation
		newLines.add("");
		newLines.add(lines.get(iput.getLine()-1).replaceAll("iput-object", "iget-object"));
		newLines.add("");
		newLines.add("    invoke-virtual {"+varName+"}, "+objectClass+"->requestFocus()Z");
		newLines.add("");
		
		for(int i=iput.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}

		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {iput.getLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" focus has been requested for the element found at line "+mLocation.getLine());
		writer.newLine();
		writer.flush();
		return true;
	}

}
