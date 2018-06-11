package uniandes.tsdl.mutapk.detectors;

import java.util.List;

import uniandes.tsdl.mutapk.model.location.MutationLocation;

public abstract class TextBasedDetector  extends MutationLocationDetector {

	
	public abstract List<MutationLocation> analyzeApp(String rootPath) throws Exception;

}
