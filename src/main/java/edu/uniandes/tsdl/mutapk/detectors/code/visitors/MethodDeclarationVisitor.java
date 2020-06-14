package edu.uniandes.tsdl.mutapk.detectors.code.visitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeVisitor;
import org.antlr.runtime.tree.TreeVisitorAction;

import edu.uniandes.tsdl.antlr.smaliParser;

public class MethodDeclarationVisitor extends TreeVisitor{
	
	ArrayList<String> calls;

	public MethodDeclarationVisitor() {
		this.calls = new ArrayList<>();
	}

	@Override
	public Object visit(Object tt, TreeVisitorAction action) {
		CommonTree t = (CommonTree) tt;
		boolean result = isValidLocation(t);
		if(result) {
			processNode(t);
			return null;
		}
		return super.visit(t, action);
	}

	private void processNode(CommonTree t) {

		String unitName = t.getFirstChildWithType(smaliParser.CLASS_DESCRIPTOR).getText();
		String methodName = t.getFirstChildWithType(smaliParser.SIMPLE_NAME).getText();
		CommonTree methodProt = (CommonTree) t.getFirstChildWithType(smaliParser.I_METHOD_PROTOTYPE); 
		String returnType = methodProt.getFirstChildWithType(smaliParser.I_METHOD_RETURN_TYPE).getChild(0).getText();

		List<CommonTree> parameters = (List<CommonTree>) methodProt.getChildren();

		String params = "(";
		for (Iterator<CommonTree> iterator2 = parameters.iterator(); iterator2.hasNext();) {
			CommonTree commonTree = (CommonTree) iterator2.next();

			if (commonTree.getType() != smaliParser.I_METHOD_RETURN_TYPE) {
				params += commonTree.getText();
			}
		}
		params += ")";
		calls.add(unitName+"&&"+methodName+params+returnType);
	}

	private boolean isValidLocation(CommonTree t) {

//			System.out.println("	"+t.getType()+ " - "+ t.getText());
//			System.out.println("	"+t.toStringTree());
		if (t.getType() == smaliParser.I_STATEMENT_FORMAT35c_METHOD || t.getType() == smaliParser.I_STATEMENT_FORMAT3rc_METHOD) {
			return true;
		}

		return false;
	}

	/**
	 * @return the calls
	 */
	public ArrayList<String> getCalls() {
		return calls;
	}
}
