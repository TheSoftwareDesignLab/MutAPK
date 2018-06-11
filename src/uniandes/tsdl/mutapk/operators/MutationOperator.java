package uniandes.tsdl.mutapk.operators;

import java.io.BufferedWriter;

import uniandes.tsdl.mutapk.model.location.MutationLocation;

public interface MutationOperator {

	boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception;
	
}
