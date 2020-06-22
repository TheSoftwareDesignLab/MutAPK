package edu.uniandes.tsdl.mutapk.operators;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;

public interface MutationOperator {

	boolean performMutation(MutationLocation location, BufferedWriter writer, int mutantIndex) throws IOException, ParserConfigurationException, SAXException;
	
}
