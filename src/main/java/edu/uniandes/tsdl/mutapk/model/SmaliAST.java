package edu.uniandes.tsdl.mutapk.model;

import org.antlr.runtime.tree.CommonTree;

public class SmaliAST {
	
	public String unitName;
	public String filePath;
	public CommonTree ast;
	
	
	public SmaliAST(String unitName, String filePath, CommonTree ast) {
		super();
		this.unitName = unitName;
		this.filePath = filePath;
		this.ast = ast;
	}


	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}


	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * @return the ast
	 */
	public CommonTree getAst() {
		return ast;
	}


	/**
	 * @param ast the ast to set
	 */
	public void setAst(CommonTree ast) {
		this.ast = ast;
	}
	
	

}
