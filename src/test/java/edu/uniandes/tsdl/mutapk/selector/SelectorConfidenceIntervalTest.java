package edu.uniandes.tsdl.mutapk.selector;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import edu.uniandes.tsdl.mutapk.model.MutationType;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;
import edu.uniandes.tsdl.mutapk.selector.ConfidenceIntervalSelector;
import edu.uniandes.tsdl.mutapk.selector.SelectorConfidenceInterval;

public class SelectorConfidenceIntervalTest {

	/**
	 * PS = Population Size;
	 * CLP = Confidence Level Percentage; 
	 * MEP = Margin Error Percentage; 
	 */

	@Test
	public void SelectorConfidenceIntervalIndividual100PS99CLP1MEPTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(1, 100);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 100, true, 99, 1);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(100, newmutationLocation.size());
	}
	
	@Test
	public void SelectorConfidenceIntervalIndividual10000PS99CLP10MEPTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(1, 10000);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 10000, true, 99, 10);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(164, newmutationLocation.size());
	}
	
	@Test
	public void SelectorConfidenceIntervalIndividual1000PS90CLP10MEPTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(1, 1000);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 1000, true, 90, 10);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(64, newmutationLocation.size());
	}
	
	@Test
	public void SelectorConfidenceIntervalIndividual10000PS85CLP10MEPTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(1, 10000);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 10000, true, 85, 10);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(52, newmutationLocation.size());
	}
	
	@Test
	public void SelectorConfidenceIntervalIndividual10000PS85CLP10MEP2TypesTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(2, 10000);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 10000, true, 85, 10);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(104, newmutationLocation.size());
	}
	
	@Test
	public void SelectorConfidenceIntervalIndividual1000PS90CLP10MEP3TypesTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(3, 1000);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 1000, true, 90, 10);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(192, newmutationLocation.size());
	}
	
	@Test
	public void SelectorConfidenceIntervalAll100PS99CLP1MEP2TypesTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(2, 100);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 200, false, 99, 1);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(198, newmutationLocation.size());

	}
	
	@Test
	public void SelectorConfidenceIntervalAll10000PS99CLP10MEP2TypesTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(2, 10000);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 20000, false, 99, 10);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(166, newmutationLocation.size());
		
	}
	
	@Test
	public void SelectorConfidenceIntervalIndividual10000PS85CLP10MEP5TypesTest() {
		HashMap<MutationType, List<MutationLocation>> locations = creationLocations(5, 10000);
		ConfidenceIntervalSelector CIMS = new ConfidenceIntervalSelector();
		SelectorConfidenceInterval selectorConfidenceInterval = new SelectorConfidenceInterval(true, false, 50000, false, 85, 10);
	
		List<MutationLocation> newmutationLocation = CIMS.mutantSelector(locations, selectorConfidenceInterval);
		assertEquals(50, newmutationLocation.size());
	}
	
	private HashMap<MutationType, List<MutationLocation>> creationLocations(int numberTypes, int sampleSize) {
		HashMap<MutationType, List<MutationLocation>> locations = new HashMap<MutationType, List<MutationLocation>>();
		MutationType[] mutationTypes = MutationType.values();
		MutationType mutationType = null;
		for (int i = 0; i < numberTypes; i++) {
			mutationType = mutationTypes[i];
			locations.put(mutationType, creationMutationLocationList(sampleSize));
		}
		return locations;
	}

	private List<MutationLocation> creationMutationLocationList(int sampleSize) {
		List<MutationLocation> mutationLocations = new LinkedList<MutationLocation>();
		
		MutationLocation mutationLocation = null;
		MutationType mutationType = MutationType.ACTIVITY_NOT_DEFINED;
		for (int i = 0; i < sampleSize; i++) {
			mutationLocation = new MutationLocation();
			mutationLocation.setEndColumn(i);
			mutationLocation.setEndLine(i);
			mutationLocation.setFilePath(String.valueOf(i));
			mutationLocation.setLength(i);
			mutationLocation.setLine(i);
			mutationLocation.setStartColumn(i);
			mutationLocation.setStartLine(i);
			mutationLocation.setType(mutationType);
			mutationLocations.add(mutationLocation);
		}
		return mutationLocations;
	}

}
