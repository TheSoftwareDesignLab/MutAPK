package edu.uniandes.tsdl.mutapk.operators;

import java.io.BufferedWriter;

import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;

public interface MutationOperator {

	boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws Exception;
	
}
