package uniandes.tsdl.mutapk.operators.activity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class NullIntent implements MutationOperator{

	@SuppressWarnings("unchecked")
	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws IOException {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree parent = (CommonTree) mLocation.getTree().getParent();
		List<CommonTree> hijos = (List<CommonTree>)parent.getChildren();
		String intentInstanceLineVar = mLocation.getTree().getChild(1).getChild(0).toString();
		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());
		
		
		//Add lines before the MutationLocation
		for(int i=0; i < mLocation.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
		
		
		//Apply mutation
		newLines.add("    const/4 "+intentInstanceLineVar+", 0x0");
		
		
		//Add lines after the MutationLocation
		for(int i=mLocation.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}
		
		int[] mutatedlines = new int[]{mLocation.getLine()};
		String mutatedLines = ""+mLocation.getLine();
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), mutatedlines, writer);
		writer.write("	For mutant "+mutantIndex+" intent defined at lines "+mutatedLines+" has been defined as null");
		writer.newLine();
		writer.flush();
		return true;
	}

}
