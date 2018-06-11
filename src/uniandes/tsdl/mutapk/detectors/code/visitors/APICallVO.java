package uniandes.tsdl.mutapk.detectors.code.visitors;

import org.antlr.runtime.tree.CommonTree;

public class APICallVO {
	
	private CommonTree tree;
	private int line;
	private String filePath;
	private int[] muTypes;
	
	public APICallVO(CommonTree tree, int line, String filePath, int[] muTypes) {
		super();
		this.tree = tree;
		this.line = line;
		this.filePath = filePath;
		this.muTypes = muTypes;
	}
	
	public CommonTree getTree() {
		return tree;
	}
	public void setTree(CommonTree tree) {
		this.tree = tree;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int[] getMuTypes() {
		return muTypes;
	}

	public void setMuTypes(int[] muTypes) {
		this.muTypes = muTypes;
	}
	
}
