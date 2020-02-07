package edu.uniandes.tsdl.mutapk.detectors;

import java.util.List;

import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;

public abstract class TextBasedDetector  extends MutationLocationDetector {

	
	public abstract List<MutationLocation> analyzeApp(String rootPath) throws Exception;

}
