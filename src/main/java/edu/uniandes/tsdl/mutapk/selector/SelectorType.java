package edu.uniandes.tsdl.mutapk.selector;

public class SelectorType {

	private final boolean isConfidenceInterval;
	private final boolean isAPK;
	private final int totalMutants;
	
	public SelectorType(boolean isConfidenceInterval, boolean isAPK, int totalMutants) {
		this.isConfidenceInterval = isConfidenceInterval;
		this.isAPK = isAPK;
		this.totalMutants = totalMutants;
	}

	public boolean isConfidenceInterval() {
		return isConfidenceInterval;
	}

	public boolean isAPK() {
		return isAPK;
	}
	
	public int getTotalMutants() {
		return totalMutants;
	}

}
