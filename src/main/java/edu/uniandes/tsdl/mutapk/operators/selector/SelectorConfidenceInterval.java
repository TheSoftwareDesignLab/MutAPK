package edu.uniandes.tsdl.mutapk.operators.selector;


public class SelectorConfidenceInterval extends SelectorType {
	
	private final boolean isCIIndividual;
	private final boolean isCIAll;
	private final int confidenceLevel;
	private final int marginError;

	/**
	 * CI is Confidence Interval
	 * 
	 * @param isConfidenceInterval
	 * @param isAPK
	 * @param isCIIndividual
	 * @param isCIAll
	 * @param confidenceLevel
	 * @param marginError
	 */
	public SelectorConfidenceInterval(boolean isConfidenceInterval, boolean isAPK, int totalMutants, boolean isCIIndividual, boolean isCIAll, int confidenceLevel, int marginError) {
		super(isConfidenceInterval, isAPK, totalMutants);
		this.isCIIndividual = isCIIndividual;
		this.isCIAll = isCIAll;
		this.confidenceLevel = confidenceLevel;
		this.marginError = marginError;
	}
	

	public boolean isCIIndividual() {
		return isCIIndividual;
	}

	public boolean isCIAll() {
		return isCIAll;
	}

	public int confidenceLevel() {
		return confidenceLevel;
	}

	public int marginError() {
		return marginError;
	}


	
}
