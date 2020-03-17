package edu.uniandes.tsdl.mutapk.operators.backend;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import edu.uniandes.tsdl.mutapk.helper.FileHelper;
import edu.uniandes.tsdl.mutapk.helper.Helper;
import edu.uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;
import edu.uniandes.tsdl.mutapk.operators.MutationOperator;

public class LongConnectionTimeOut implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws IOException  {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree tree = mLocation.getTree();
		String varName = tree.getChild(1).getChild(1).toStringTree();
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < mLocation.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
//		//Apply mutation
		newLines.add("    const "+varName+", 0x186a0");
		newLines.add("");
		for(int i=mLocation.getLine()-1; i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}
//
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {mLocation.getLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" time assigned to setConnectionTimeout method at line "+mLocation.getLine()+" has been updated to 100000 milliseconds");
		writer.newLine();
		writer.flush();
		
		return true;
	}

}
