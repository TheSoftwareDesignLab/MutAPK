package edu.uniandes.tsdl.mutapk.model;

import java.util.HashSet;
import java.util.Set;

import org.antlr.runtime.tree.CommonTree;

public class CallGraphNode {
	
	public String id;
	public String unitName;
	public CommonTree methodASTNode;
	public Set<CallGraphNode> callers;
	public Set<CallGraphNode> callees;
	
	
	public CallGraphNode(String id, String unitName, CommonTree methodASTNode) {
		super();
		this.id = id;
		this.unitName = unitName;
		this.methodASTNode = methodASTNode;
		callers = new HashSet<CallGraphNode>();
		callees = new HashSet<CallGraphNode>();
	}
	
	@Override
	public boolean equals(Object obj) {
		CallGraphNode temp = (CallGraphNode) obj;
		return ((temp.getId().equals(this.getId())) && (temp.getUnitName().equals(this.getUnitName())));
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the methodASTNode
	 */
	public CommonTree getMethodASTNode() {
		return methodASTNode;
	}

	/**
	 * @param methodASTNode the methodASTNode to set
	 */
	public void setMethodASTNode(CommonTree methodASTNode) {
		this.methodASTNode = methodASTNode;
	}

	/**
	 * @return the called
	 */
	public Set<CallGraphNode> getCallers() {
		return callers;
	}

	/**
	 * @param called the called to set
	 */
	public void setCallers(Set<CallGraphNode> called) {
		this.callers = called;
	}

	/**
	 * @return the callee
	 */
	public Set<CallGraphNode> getCallees() {
		return callees;
	}

	/**
	 * @param callee the callee to set
	 */
	public void setCallees(Set<CallGraphNode> callee) {
		this.callees = callee;
	}

	public void addCallee(CallGraphNode cGN) {
		callees.add(cGN);
	}

	public void addCaller(CallGraphNode cGN) {
		
		callers.add(cGN);
		
	}
	
	
	
}
