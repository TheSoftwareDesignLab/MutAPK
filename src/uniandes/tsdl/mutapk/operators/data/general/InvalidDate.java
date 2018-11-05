package uniandes.tsdl.mutapk.operators.data.general;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.HexadecimalGenerator;
import uniandes.tsdl.mutapk.helper.StringGenerator;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class InvalidDate implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree tree = mLocation.getTree();
		String varName = tree.getChild(1).getChild(1).toStringTree();
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < mLocation.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
		String newVarValue = "0x"+HexadecimalGenerator.generateRandomHexaLong();
//		//Apply mutation
		newLines.add("    const-wide "+varName+", "+newVarValue);
		newLines.add("");
		newLines.add(lines.get(mLocation.getLine()-1));
		newLines.add("");
		
		for(int i=mLocation.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}
//
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {mLocation.getLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" Date at line "+mLocation.getLine()+" has been updated to "+newVarValue);
		writer.newLine();
		writer.flush();
		
		return true;
	}

}
