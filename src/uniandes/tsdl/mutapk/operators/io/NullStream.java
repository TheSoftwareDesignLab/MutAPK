package uniandes.tsdl.mutapk.operators.io;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class NullStream implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {

		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree tree = mLocation.getTree();
		String varName = tree.getChild(1).getChild(0).toString();

		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < mLocation.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
		//				//Apply mutation
		newLines.add("    const/4 "+varName+", 0x0");
		newLines.add("");

		for(int i=mLocation.getLine()-1; i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}
		
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {mLocation.getLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" "+((mLocation.getType().getId()==33)?"Input":"Output")+" Stream at line "+mLocation.getLine()+" has been set to null before calling close() method");
		writer.newLine();
		writer.flush();
		return true;

	}

}
