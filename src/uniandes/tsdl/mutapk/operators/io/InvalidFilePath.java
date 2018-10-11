package uniandes.tsdl.mutapk.operators.io;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.ASTHelper;
import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.StringGenerator;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class InvalidFilePath implements MutationOperator{

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		String paramName = mLocation.getTree().getChild(1).getChild(1).toStringTree();
		String mutationPath = StringGenerator.generateRandomString();
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < mLocation.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
		
		//Apply mutation
		newLines.add("");
		newLines.add("    const-string v9, \""+mutationPath+"\"");
		newLines.add("");
		newLines.add(lines.get(mLocation.getLine()-1).replace(paramName+"}", "v9}"));
		newLines.add("");
		
		for(int i=mLocation.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}

		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {mLocation.getLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" the path of File at line "+mLocation.getLine()+" has been replaced by "+mutationPath);
		writer.newLine();
		writer.flush();
		return true;
		
		
	}

}