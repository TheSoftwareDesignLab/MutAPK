package edu.uniandes.tsdl.mutapk.operators.data.android;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import edu.uniandes.tsdl.mutapk.helper.ASTHelper;
import edu.uniandes.tsdl.mutapk.helper.FileHelper;
import edu.uniandes.tsdl.mutapk.helper.Helper;
import edu.uniandes.tsdl.mutapk.helper.StringGenerator;
import edu.uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;
import edu.uniandes.tsdl.mutapk.operators.MutationOperator;
import edu.uniandes.tsdl.mutapk.processors.MutationsProcessor;
import edu.uniandes.tsdl.antlr.smaliParser;

public class InvalidURI implements MutationOperator {

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
		//String newVarName = varName.substring(0, 1)+(Integer.parseInt(varName.substring(1))+2);
		String newVarValue = StringGenerator.generateRandomString();
//		//Apply mutation
		newLines.add("");
		newLines.add("    const-string "+varName+", \""+newVarValue+"\"");
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
		writer.write("	For mutant "+mutantIndex+" URI location has been set to "+newVarValue+" at line "+mLocation.getLine());
		writer.newLine();
		writer.flush();
		
		return true;
		
	}

}
