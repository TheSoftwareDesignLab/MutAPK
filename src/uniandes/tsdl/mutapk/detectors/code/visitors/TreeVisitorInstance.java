package uniandes.tsdl.mutapk.detectors.code.visitors;

import java.util.HashSet;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeVisitor;
import org.antlr.runtime.tree.TreeVisitorAction;

import uniandes.tsdl.antlr.smaliParser;
import uniandes.tsdl.mutapk.helper.ASTHelper;

public class TreeVisitorInstance extends TreeVisitor{
	
	private HashSet<APICallVO> calls;
	String filePath;
	
	public TreeVisitorInstance(String filePath) {
		calls = new HashSet<APICallVO>();
		this.filePath = filePath;
	}
	
	@Override
	public Object visit(Object tt, TreeVisitorAction action) {
		CommonTree t = (CommonTree) tt;
		int[] muTypes = ASTHelper.isValidLocation(t);
		if(muTypes[0]!=-1){
//			System.out.println(t.toStringTree());
//			System.out.println(t.getChildIndex());
//			System.out.println(t.getParent().getChild(t.getChildIndex()).toStringTree());
			calls.add(new APICallVO(t, t.getLine(), filePath, muTypes));
		}
		return super.visit(t, action);
	}
	
	

	public HashSet<APICallVO> getCalls() {
		return calls;
	}
}
