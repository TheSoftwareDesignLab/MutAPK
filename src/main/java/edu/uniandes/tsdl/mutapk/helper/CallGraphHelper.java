package edu.uniandes.tsdl.mutapk.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.antlr.runtime.tree.CommonTree;
import edu.uniandes.tsdl.antlr.smaliParser;
import edu.uniandes.tsdl.mutapk.detectors.code.visitors.MethodDeclarationVisitor;
import edu.uniandes.tsdl.mutapk.model.CallGraphNode;
import edu.uniandes.tsdl.mutapk.model.SmaliAST;

public class CallGraphHelper {

	public static HashMap<String, HashMap<String, CallGraphNode>> getCallGraph(HashMap<String, SmaliAST> smaliASTs) {

		HashMap<String, HashMap<String, CallGraphNode>> callGraph = new HashMap<String, HashMap<String, CallGraphNode>>();

		// Add all methods ids to hashmap
		for(Entry<String, SmaliAST> entry : smaliASTs.entrySet()) {
			callGraph.put(entry.getKey(), generateSmaliCGNodes(entry.getValue()));
		}
		int contador = 0;

		for(Entry<String, HashMap<String, CallGraphNode>> entry : callGraph.entrySet()) {
//			System.out.println(entry.getKey());
			for(Entry<String, CallGraphNode> entryy : entry.getValue().entrySet()) {
				//				System.out.println("	"+entryy.getKey());
				CallGraphNode cGN = entryy.getValue();
				MethodDeclarationVisitor mtv = new MethodDeclarationVisitor();
				mtv.visit(entryy.getValue().getMethodASTNode(), null);
				
				ArrayList<String> calls = mtv.getCalls();
				for (Iterator<String> iterator = calls.iterator(); iterator.hasNext();) {
					String methodCall = (String) iterator.next();
					
					String[] methodCallSegments = methodCall.split("&&");
					if(callGraph.get(methodCallSegments[0]) != null && callGraph.get(methodCallSegments[0]).get(methodCallSegments[1]) != null){
						CallGraphNode calledCGN = callGraph.get(methodCallSegments[0]).get(methodCallSegments[1]);
						calledCGN.addCaller(cGN);
						cGN.addCallee(calledCGN);
					} else if(!methodCall.startsWith("Ljava") 
							&& !methodCall.startsWith("Landroid") 
							&& !methodCallSegments[1].startsWith("findViewById")
							&& !methodCallSegments[1].startsWith("getActivity")
							&& !methodCallSegments[1].startsWith("getContentResolver")
//							&& !methodCallSegments[1].startsWith("getParent")
							) {
//						System.out.println("	###"+methodCall);
						contador++;
					}
				}
			}
		}
		System.out.println("NotDefinedButCalled			| "+contador);
		return callGraph;
	}

	private static HashMap<String, CallGraphNode> generateSmaliCGNodes(SmaliAST smaliASTNode) {

		CommonTree smaliAST = smaliASTNode.getAst();

		HashMap<String, CallGraphNode> smaliFileCGNodes = new HashMap<String, CallGraphNode>();

		CommonTree methodsElem = (CommonTree) smaliAST.getFirstChildWithType(smaliParser.I_METHODS);
		List<CommonTree> methods = (List<CommonTree>) methodsElem.getChildren();
		if( methods != null ) {
			for (Iterator<CommonTree> iterator = methods.iterator(); iterator.hasNext();) {
				CommonTree t = (CommonTree) iterator.next();

				String methodName = t.getFirstChildWithType(smaliParser.SIMPLE_NAME).getText();

				CommonTree methodPrototype = (CommonTree) t.getFirstChildWithType(smaliParser.I_METHOD_PROTOTYPE);
				List<CommonTree> parameters = (List<CommonTree>) methodPrototype.getChildren();

				String returnType = methodPrototype.getFirstChildWithType(smaliParser.I_METHOD_RETURN_TYPE).getChild(0).getText();

				String params = "(";
				for (Iterator<CommonTree> iterator2 = parameters.iterator(); iterator2.hasNext();) {
					CommonTree commonTree = (CommonTree) iterator2.next();

					if (commonTree.getType() != smaliParser.I_METHOD_RETURN_TYPE) {
						params += commonTree.getText();
					}
				}
				params += ")";

				String methodID = methodName+params+returnType;
				CallGraphNode cGN = new CallGraphNode(methodID, smaliASTNode.getUnitName(), t);

				smaliFileCGNodes.put(methodID, cGN);
			}
		}
		return smaliFileCGNodes;
	}
}
