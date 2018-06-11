package uniandes.tsdl.mutapk.model.location;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.mutapk.model.MutationType;

public class ASTMutationLocation extends MutationLocation {
	private CommonTree tree;
	
	
	public CommonTree getTree() {
		return tree;
	}


	public void setTree(CommonTree tree) {
		this.tree = tree;
	}


	public static MutationLocation buildLocation( String path, int startLine, int endLine, int startCol, int endCol, int lineNum, int length, MutationType mType, CommonTree tree) {
		ASTMutationLocation location = new ASTMutationLocation();
		location.setFilePath(path);
		location.setStartLine(startLine);
		location.setEndLine(endLine);
		location.setStartColumn(startCol);
		location.setEndColumn(endCol);
		location.setType(mType);
		location.setLine(lineNum);
        location.setLength(length);
        location.setTree(tree);
		return location;
	}
}
