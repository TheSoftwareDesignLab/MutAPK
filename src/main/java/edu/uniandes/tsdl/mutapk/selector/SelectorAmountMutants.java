package edu.uniandes.tsdl.mutapk.selector;

public class SelectorAmountMutants extends SelectorType{

	
	private final int amountMutants; 
	
	public SelectorAmountMutants(boolean isConfidenceInterval, boolean isAPK, int totalMutants, int amountMutants) {
		super(isConfidenceInterval, isAPK, totalMutants);
		this.amountMutants = amountMutants;
	}

	public int getAmountMutants() {
		return amountMutants;
	}
	
}
