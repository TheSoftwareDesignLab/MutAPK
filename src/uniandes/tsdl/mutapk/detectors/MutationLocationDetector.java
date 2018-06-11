package uniandes.tsdl.mutapk.detectors;

import java.util.List;

import uniandes.tsdl.mutapk.model.MutationType;
import uniandes.tsdl.mutapk.model.location.MutationLocation;

public abstract class MutationLocationDetector {
	
	//Folder path where the analysis starts
	protected String rootPath;
	protected MutationType type;
	
	public abstract List<MutationLocation> analyzeApp(String rootPath) throws Exception;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public MutationType getType() {
		return type;
	}

	public void setType(MutationType type) {
		this.type = type;
	}

	
	
	
	

}
