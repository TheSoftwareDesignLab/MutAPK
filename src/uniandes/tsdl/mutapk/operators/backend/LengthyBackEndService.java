package uniandes.tsdl.mutapk.operators.backend;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;

import uniandes.tsdl.antlr.smaliParser;
import uniandes.tsdl.mutapk.helper.ASTHelper;
import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.model.location.ASTMutationLocation;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class LengthyBackEndService implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {

		
		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());
		ASTMutationLocation mLocation = (ASTMutationLocation) location;
		CommonTree method = (CommonTree) mLocation.getTree().getAncestor(smaliParser.I_METHOD);
		CommonTree classs = (CommonTree) ((CommonTree) mLocation.getTree().getAncestors().get(0)).getFirstChildWithType(smaliParser.CLASS_DESCRIPTOR);
		
		for(int i=0; i < method.getLine()-1; i++){
			newLines.add(lines.get(i));
		}
		
		newLines.add(".method private delay()V");
		newLines.add("    .locals 2");
		newLines.add("");
		newLines.add("    .line 332");
		newLines.add("    const-wide/16 v0, 0x2710");
		newLines.add("");
		newLines.add("    :try_start_0");
		newLines.add("    invoke-static {v0, v1}, Ljava/lang/Thread;->sleep(J)V");
		newLines.add("    :try_end_0");
		newLines.add("    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0");
		newLines.add("");
		newLines.add("    .line 335");
		newLines.add("    goto :goto_0");
		newLines.add("");
		newLines.add("    .line 333");
		newLines.add("    :catch_0");
		newLines.add("    move-exception v0");
		newLines.add("");
		newLines.add("    .line 334");
		newLines.add("    .local v0, \"e\":Ljava/lang/InterruptedException;");
		newLines.add("    invoke-virtual {v0}, Ljava/lang/InterruptedException;->printStackTrace()V");
		newLines.add("");
		newLines.add("    .line 336");
		newLines.add("    .end local v0    # \"e\":Ljava/lang/InterruptedException;");
		newLines.add("    :goto_0");
		newLines.add("    return-void");
		newLines.add(".end method");
		
		for (int i = method.getLine()-1; i < mLocation.getLine(); i++) {
			newLines.add(lines.get(i));
		}
		newLines.add("    invoke-direct/range {p0 .. p0}, Lcom/fsck/k9/mail/store/webdav/WebDavFolder;->delay()V");

		for(int i=location.getLine(); i < lines.size() ; i++){
			newLines.add(lines.get(i));
		}

		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {location.getLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" a large delay has been injected after backend response at line "+location.getLine());
		writer.newLine();
		writer.flush();
		return true;
	}

}
