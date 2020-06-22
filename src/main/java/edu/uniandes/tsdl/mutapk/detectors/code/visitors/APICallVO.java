package edu.uniandes.tsdl.mutapk.detectors.code.visitors;

import org.antlr.runtime.tree.CommonTree;

public class APICallVO {
	
	private CommonTree tree;
	private int line;
	private int[] muTypes;
	
	public APICallVO(CommonTree tree, int line, int[] muTypes) {
		super();
		this.tree = tree;
		this.line = line;
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

	public int[] getMuTypes() {
		return muTypes;
	}

	public void setMuTypes(int[] muTypes) {
		this.muTypes = muTypes;
	}
	
}
