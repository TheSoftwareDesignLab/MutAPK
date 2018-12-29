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

public class NullValueIntentPutExtra implements MutationOperator{

	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws IOException {
		
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree parent = (CommonTree) mLocation.getTree().getParent();
		List<CommonTree> hijos = (List<CommonTree>)parent.getChildren();
		String extraNamePos = hijos.get(1).getChild(2).getText();
		String extraTypeText = hijos.get(4).getChild(2).getText();
		for (int i = 3; i < hijos.get(4).getChildCount(); i++) {
			extraTypeText += hijos.get(4).getChild(i).getText();
		}
		int extraTypePos = hijos.get(4).getChild(2).getCharPositionInLine();
//		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());
//		
		//Add lines before the MutationLocation
		for(int i=0; i < mLocation.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
//		
//		
		//Apply mutation
		newLines.add("    new-array "+extraNamePos+", "+extraNamePos+", [Landroid/os/Parcelable;");
		newLines.add("");
		String invoke = lines.get(location.getLine()-1);//.replace(extraNamePos, "v9");
		String newInvoke = invoke.substring(0, extraTypePos)+"[Landroid/os/Parcelable;"+invoke.substring(extraTypePos+extraTypeText.length());
//		System.out.println(newInvoke);
		newLines.add(newInvoke);
		for(int i=location.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}
//		
//		int[] mutatedlines = new int[]{intentInstanceLine, contextInstanceLine, classComponentLine, mLocation.getLine()};
//		String mutatedLines = "{ ";
//		for (int i = 0; i < mutatedlines.length; i++) {
//			mutatedLines += mutatedlines[i]+", ";
//		}
//		mutatedLines = mutatedLines.substring(0,mutatedLines.length()-2)+" }";
		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {location.getStartLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" extra intent value at line "+location.getStartLine()+" has been set to 'new Parcelable[0]'");
		writer.newLine();
		writer.flush();
		return true;
	}

}
