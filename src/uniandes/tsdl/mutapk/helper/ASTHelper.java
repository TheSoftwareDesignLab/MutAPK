package uniandes.tsdl.mutapk.helper;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.tree.CommonTree;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import uniandes.tsdl.antlr.smaliParser;
import uniandes.tsdl.jflex.smaliFlexLexer;
import uniandes.tsdl.mutapk.detectors.code.visitors.ClassInstanceVisitor;
import uniandes.tsdl.mutapk.detectors.code.visitors.MethodCallVO;
import uniandes.tsdl.mutapk.detectors.code.visitors.MethodCallVisitor;
import uniandes.tsdl.mutapk.detectors.code.visitors.MethodDeclarationVO;
import uniandes.tsdl.mutapk.detectors.code.visitors.MethodDeclarationVisitor;
import uniandes.tsdl.smali.LexerErrorInterface;

public class ASTHelper {

	public static CommonTree getAST(String sourcePath) {

		FileInputStream fis = null;
		File smaliFile = new File(sourcePath);
		CommonTree t = null;
		try {
			fis = new FileInputStream(smaliFile);
			InputStreamReader reader = new InputStreamReader(fis, "UTF-8");

			LexerErrorInterface lexer = new smaliFlexLexer(reader);
			((smaliFlexLexer)lexer).setSourceFile(smaliFile);
			// System.out.println(((smaliFlexLexer)lexer).nextToken().getText());
			CommonTokenStream tokens = new CommonTokenStream((TokenSource)lexer);
			tokens.getTokens();
			smaliParser parser = new smaliParser(tokens);
			// parser.setVerboseErrors(options.verboseErrors);
			// parser.setAllowOdex(options.allowOdexOpcodes);
			// parser.setApiLevel(options.apiLevel);

			smaliParser.smali_file_return result = parser.smali_file();
			t = result.getTree();
			return t;
		} catch (Exception e){
			e.printStackTrace();
		}
		return t;
	}

	public static CommonTree getFirstUncleNamedOfType(int type, String name, CommonTree t) {
		CommonTree parent = (CommonTree) t.getParent();
		List<CommonTree> uncles = (List<CommonTree>)((CommonTree)parent.getParent()).getChildren();
		for (int i = parent.getChildIndex()+1; i < uncles.size(); i++) {
			CommonTree tempUncle = (CommonTree) uncles.get(i);
			if(tempUncle.getType()==type && tempUncle.getChild(0).toStringTree().equals(name)) {
				return tempUncle;
			}
		}
		return null;
	}

	public static CommonTree getFirstBackUncleNamedOfType(int type, String name, CommonTree t) {
		CommonTree parent = (CommonTree) t.getParent();
		List<CommonTree> uncles = (List<CommonTree>)((CommonTree)parent.getParent()).getChildren();
		for (int i = parent.getChildIndex(); i > -1; i--) {
			CommonTree tempUncle = (CommonTree) uncles.get(i);
			if(tempUncle.getType()==type && tempUncle.getChild(0).toStringTree().equals(name)) {
				return tempUncle;
			}
		}
		return null;
	}

	public static CommonTree getFirstBrotherNamedOfType(int type, String name, CommonTree t) {
		CommonTree parent = (CommonTree) t.getParent();
		List<CommonTree> brothers = (List<CommonTree>)parent.getChildren();
		for (int i = t.getChildIndex()+1; i < brothers.size(); i++) {
			CommonTree tempBrother = (CommonTree) brothers.get(i);
			if(tempBrother.getType()==type && tempBrother.getChild(0).toStringTree().equals(name)) {
				return tempBrother;
			}
		}
		return null;
	}

	public static CommonTree hasIPutAndIGet(CommonTree t) {
		CommonTree iput = getFirstUncleNamedOfType(smaliParser.I_STATEMENT_FORMAT22c_FIELD, "iput-object", t);
		if(iput!=null && iput.getLine()-t.getLine()<7)
		{
			List<CommonTree> cousins = (List<CommonTree>)iput.getChildren();
			String varName = cousins.get(4).toStringTree();
			CommonTree iget = getFirstBrotherNamedOfType(smaliParser.I_STATEMENT_FORMAT22c_FIELD, "iget-object", iput);
			while(iget!=null)
			{
				List<CommonTree> cousinss = (List<CommonTree>)iget.getChildren();
				if(cousinss.get(4).toStringTree().equals(varName)){
					return iget;
				} else {
					iget = getFirstBrotherNamedOfType(smaliParser.I_STATEMENT_FORMAT22c_FIELD, "iget-object", iget);
				}
			}
		}
		return null;
	}

	public static int[] isValidLocation(CommonTree t){
		//		if(t.getType()==159) {
		//						System.out.println(t.toStringTree());
		//						System.out.println(t.getType());
		//			System.out.println(t.getChild(2));
		//			System.out.println(t.getChild(3));
		//			System.out.println(t.getChild(2).toStringTree().equals("Ljava/net/URI;") && t.getChild(3).toStringTree().equals("<init>") );
		//		}

		if(t.getType()==smaliParser.I_STATEMENT_FORMAT35c_METHOD) {
			ArrayList<Integer> resp = new ArrayList<Integer>();
			if(t.getFirstChildWithType(smaliParser.I_REGISTER_LIST).getChildCount()==3 
					&& t.getFirstChildWithType(smaliParser.CLASS_DESCRIPTOR).toString().equals("Landroid/content/Intent;") 
					&& t.getFirstChildWithType(smaliParser.SIMPLE_NAME).toString().equals("<init>")) {
				resp.add(2);
				resp.add(6);
			} else if(t.getChild(2).toStringTree().equals("Ljava/io/File;") 
					&& t.getChild(4).getChild(1)!=null && t.getChild(4).getChild(1).toStringTree().equals("Ljava/lang/String;")) {
				resp.add(32);
			} else if(t.getChild(2).toStringTree().equals("Ljava/net/URI;") 
					&& t.getChild(3).toStringTree().equals("<init>")) {
				resp.add(17);
			} else if(t.getChild(1).getChildCount() > 1 
					&& t.getChild(2).toStringTree().equals("Ljava/util/Date;") 
					&& t.getChild(3).toStringTree().equals("<init>")) {
				resp.add(19);
			} else if(t.getChild(2).toStringTree().equals("Landroid/bluetooth/BluetoothAdapter;")
					&& t.getChild(3).toStringTree().equals("getDefaultAdapter")
					&& t.getChild(4).getChild(0).getChild(0).toStringTree().equals("Landroid/bluetooth/BluetoothAdapter;")) {
				resp.add(16);
			} else if(t.getChild(2).toStringTree().equals("Lorg/apache/http/params/HttpConnectionParams;")
					&& t.getChild(3).toStringTree().equals("setConnectionTimeout")) {
				resp.add(14);
			} else if (isNullBackendServiceReturn(t)) {
				resp.add(20);
			} else if(t.getChild(2).toStringTree().equals("Landroid/graphics/Bitmap;")
					&& t.getChild(3).toStringTree().equals("createScaledBitmap")) {
				resp.add(35);
			} else if(t.getChild(2).toStringTree().equals("Landroid/database/sqlite/SQLiteDatabase;")
					&& t.getChild(3).toStringTree().equals("rawQuery")) {
				resp.add(24);
				resp.add(25);
			} else if(t.getChild(2).toStringTree().equals("Landroid/database/Cursor;")
					&& t.getChild(3).toStringTree().equals("close")) {
				resp.add(23);
			} else if(isNullInputStream(t)) {
				resp.add(33);
			} else if(isNullOutputStream(t)) {
				resp.add(37);
			} 

			if(t.getChild(1).getChildCount()>1) {
				resp.add(22);
			}

			if(resp.size()>0) {
				int[] ret = new int[resp.size()];
				for (int i=0; i < ret.length; i++)
				{
					ret[i] = resp.get(i).intValue();
				}
				return ret;				
			}

		} else if(t.getType()==191) {
			if(t.getText().equals("putExtra")){ //InvalidKeyIntentPutExtra && NullValueIntentPutExtra
				return new int[]{4, 7}; 
			} else if(t.getText().equals("findViewById")) {
				ArrayList<Integer> resp = new ArrayList<Integer>();
				resp.add(27);
				resp.add(31);
				if (hasIPutAndIGet(t)!=null) {
					resp.add(26);
					resp.add(29);
				}
				int[] ret = new int[resp.size()];
				for (int i=0; i < ret.length; i++)
				{
					ret[i] = resp.get(i).intValue();
				}
				return ret;				
			} 
		} else if(t.getType()==161) {
			if(t.getChild(2).toStringTree().equals("Landroid/bluetooth/BluetoothAdapter;")
					&& t.getChild(3).toStringTree().equals("isEnabled")
					&& getFirstBrotherNamedOfType(smaliParser.I_STATEMENT_FORMAT21t, "if-eqz", t) != null
					&& getFirstBrotherNamedOfType(smaliParser.I_STATEMENT_FORMAT21t, "if-eqz", t).getLine()<(t.getLine()+5)) {
				return new int[]{15};
			}
		} else if(t.getType() == smaliParser.I_LOCAL) {
			if (t.getChild(2).toString().equals("Landroid/location/Location;")) {
				return new int[] {18};
			} else if(t.getChild(2).toString().equals("Lorg/apache/http/HttpResponse;")) {
				return new int[] {13,20};
			}  
		} else if(t.getType() == smaliParser.I_METHOD) {
			if (isOnCreateMethod(t)) {
				return new int[] {38};
			} else if(isOnClickMethod(t)) {
				return new int[] {36};
			}
		}

		//		} else if(false){//View.OnClickListener
		//			return new int[]{30};	
		//		} 
		return new int[]{-1};
	}

	private static boolean isNullOutputStream(CommonTree t) {
		String apis = "#Ljava/io/OutputStream;"
				+ "#Ljava/io/ByteArrayOutputStream;"
				+ "#Ljava/io/FileOutputStream;"
				+ "#Ljava/io/FilterOutputStream;"
				+ "#Ljava/io/ObjectOutputStream;"
				+ "#Ljava/io/PipedOutputStream;"
				+ "#Ljava/io/BufferedOutputStream;"
				+ "#Ljava/io/PrintStream;"
				+ "#Ljava/io/DataOutputStream;";
		if(apis.contains(t.getChild(2).toStringTree())
				&& t.getChild(3).toStringTree().equals("close")) {
			return true;
		}
		return false;
	}

	private static boolean isNullInputStream(CommonTree t) {
		String apis = "#Ljava/nio/channels/FileChannel;"
				+ "#Ljava/io/InputStream;"
				+ "#Ljava/io/BufferedInputStream;"
				+ "#Ljava/io/ByteArrayInputStream;"
				+ "#Ljava/io/DataInputStream;"
				+ "#Ljava/io/FilterInputStream;"
				+ "#Ljava/io/ObjectInputStream;"
				+ "#Ljava/io/PipedInputStream;"
				+ "#Ljava/io/SequenceInputStream;"
				+ "#Ljava/io/StringBufferInputStream;";
		if(apis.contains("#"+t.getChild(2).toStringTree()+"#")
				&& t.getChild(3).toStringTree().equals("close")) {
			return true;
		}
		return false;
	}

	private static boolean isNullBackendServiceReturn(CommonTree t) {
		CommonTree tree = (CommonTree) t.getFirstChildWithType(smaliParser.I_METHOD_PROTOTYPE);
		CommonTree treee = (CommonTree) tree.getFirstChildWithType(smaliParser.I_METHOD_RETURN_TYPE);
		String classs = treee.getChild(0).toString();
		return classs.equals("Lorg/apache/http/HttpResponse;");
	}

	private static boolean isOnCreateMethod(CommonTree t) {
		boolean resp = t.getChild(0).toString().equals("onCreate");
		if(resp) {
			CommonTree mProt = (CommonTree) t.getFirstChildWithType(smaliParser.I_METHOD_PROTOTYPE);
			resp = (mProt.getChildCount() == 2);
			if(resp) {
				resp = mProt.getChild(1).toString().equals("Landroid/os/Bundle;");
			}
		}
		return resp;
	}

	private static boolean isOnClickMethod(CommonTree t) {
		boolean resp = t.getChild(0).toString().equals("onClick");
		if(resp) {
			CommonTree mProt = (CommonTree) t.getFirstChildWithType(smaliParser.I_METHOD_PROTOTYPE);
			resp = (mProt.getChildCount() == 2);
			if(resp) {
				resp = mProt.getChild(1).toString().equals("Landroid/view/View;");
			}
		}
		return resp;
	}

}
