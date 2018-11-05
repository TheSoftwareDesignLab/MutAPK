package uniandes.tsdl.mutapk.operators.image;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class OOMLargeImage implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {

		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree tree = mLocation.getTree();
		String varWidth = tree.getChild(1).getChild(1).toString();
		String varHeight = tree.getChild(1).getChild(2).toString();

		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());

		//Add lines before the MutationLocation
		for(int i=0; i < mLocation.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
		//				//Apply mutation
		newLines.add("    const "+varHeight+", 0x186a0");
		newLines.add("");
		newLines.add(lines.get(mLocation.getLine()-1).replaceAll(varWidth, varHeight));

		for(int i=mLocation.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}
		
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {mLocation.getLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" Bitmap created at line "+mLocation.getLine()+" has been scaled to 100000x100000 pixels");
		writer.newLine();
		writer.flush();

		return true;
	}

}
