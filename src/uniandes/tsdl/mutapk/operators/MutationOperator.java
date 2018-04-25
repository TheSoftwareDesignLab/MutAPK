package uniandes.tsdl.mutapk.operators;

import uniandes.tsdl.mutapk.model.location.MutationLocation;

public interface MutationOperator {

	boolean performMutation(MutationLocation location);
	
}
