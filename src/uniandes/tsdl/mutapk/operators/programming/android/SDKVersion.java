package uniandes.tsdl.mutapk.operators.programming.android;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import uniandes.tsdl.mutapk.helper.FileHelper;
import uniandes.tsdl.mutapk.helper.Helper;
import uniandes.tsdl.mutapk.helper.IntegerGenerator;
import uniandes.tsdl.mutapk.model.location.MutationLocation;
import uniandes.tsdl.mutapk.operators.MutationOperator;

public class SDKVersion implements MutationOperator {

	@Override
	public boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception {

		List<String> newLines = new ArrayList<String>();
		List<String> lines = FileHelper.readLines(location.getFilePath());
		boolean isMinSDK = false;
		boolean isTargetSDK = false;
		boolean isMaxSDK = false;
		String toMutate = "";
		int newVersion = 0;

		for (int i = 0; i < lines.size(); i++) {

			String currLine = lines.get(i);

			if (i == location.getLine()) {
				// Apply mutation

				if (currLine.contains(Helper.MIN_SDK_VERSION)
						&& (location.getStartColumn() - currLine.indexOf(Helper.MIN_SDK_VERSION) > 0)
						&& (location.getStartColumn() - currLine.indexOf(Helper.MIN_SDK_VERSION) < 24)) {
					isMinSDK = true;
				} else if (currLine.contains(Helper.MAX_SDK_VERSION)
						&& (location.getStartColumn() - currLine.indexOf(Helper.MAX_SDK_VERSION) > 0)
						&& (location.getStartColumn() - currLine.indexOf(Helper.MAX_SDK_VERSION) < 24)) {
					isMaxSDK = true;
				} else {
					isTargetSDK = true;
				}
				String sub1 = currLine.substring(0, location.getStartColumn());
				toMutate = currLine.substring(location.getStartColumn(), location.getEndColumn());
				String sub2 = currLine.substring(location.getEndColumn());

				int currVersion = Integer.parseInt(toMutate);
				newVersion = IntegerGenerator.generateRandomInt(Helper.MIN_VERSION, Helper.MAX_VERSION);

				while (newVersion == currVersion) {
					newVersion = IntegerGenerator.generateRandomInt(Helper.MIN_VERSION, Helper.MAX_VERSION);
				}

				currLine = sub1 + newVersion + sub2;
			}

			newLines.add(currLine);
		}

		FileHelper.writeLines(location.getFilePath(), newLines);
		Helper.mutationSuccess(mutantIndex);
		Helper.writeBasicLogInfo(mutantIndex, location.getFilePath(), location.getType().getName(), new int[] {location.getStartLine()}, writer);
		writer.write("	For mutant "+mutantIndex+" the "+((isMinSDK)?"minSDKVersion":(isMaxSDK)?"maxSDKVersion":"targetSDKVersion")+" has been update from "+toMutate+" to "+newVersion);
		writer.newLine();
		writer.flush();

		return true;
	}

}
