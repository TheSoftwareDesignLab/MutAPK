package edu.uniandes.tsdl.mutapk.operators.selector;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import edu.uniandes.tsdl.mutapk.detectors.MutationLocationListBuilder;
import edu.uniandes.tsdl.mutapk.model.MutationType;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;

public class ConfidenceIntervalSelector implements Selector {

	public ConfidenceIntervalSelector() {}

	@Override
	public List<MutationLocation> mutantSelector(HashMap<MutationType, List<MutationLocation>> locations,
			SelectorType selectorType) {
		if(selectorType instanceof SelectorConfidenceInterval) {
			SelectorConfidenceInterval selectorConfidenceInterval = (SelectorConfidenceInterval) selectorType;
			int confidenceLevel = selectorConfidenceInterval.confidenceLevel();
			int marginError = selectorConfidenceInterval.marginError();
			if(selectorConfidenceInterval.isCIIndividual()) {
				HashMap<MutationType, List<MutationLocation>> newListLocations = getNewListLocationsIndividual(locations,
						confidenceLevel, marginError);
				return MutationLocationListBuilder.buildList(newListLocations);
			}else {
				int allSampleSize = TargetPopulation.calculateSampleSize(selectorConfidenceInterval.getTotalMutants(), confidenceLevel, marginError);
				int individualSampleSize = (int) Math.round((double) allSampleSize/locations.size());
				HashMap<MutationType, List<MutationLocation>> newListLocations = getNewListLocationAll(locations,
						individualSampleSize);
				return MutationLocationListBuilder.buildList(newListLocations);
			}
		}else {
			throw new UnsupportedOperationException("The " + selectorType.getClass().getSimpleName() + "r is not implemented ");
		}
	}

	private HashMap<MutationType, List<MutationLocation>> getNewListLocationAll(
			HashMap<MutationType, List<MutationLocation>> locations, int individualSampleSize) {
		HashMap<MutationType, List<MutationLocation>> newListLocations = new HashMap<MutationType, List<MutationLocation>>();
		Set<MutationType> mutationKeys = locations.keySet();
		List<MutationLocation> mutationsLocation = null;
		for (MutationType mutationKey : mutationKeys) {
			mutationsLocation = locations.get(mutationKey);
			newListLocations.put(mutationKey, IndividualConfidenceInterval(mutationsLocation, individualSampleSize));
		}
		return newListLocations;
	}

	private HashMap<MutationType, List<MutationLocation>> getNewListLocationsIndividual(
			HashMap<MutationType, List<MutationLocation>> locations, int confidenceLevel, int marginError) {
		HashMap<MutationType, List<MutationLocation>> newListLocations = new HashMap<MutationType, List<MutationLocation>>();
		Set<MutationType> mutationKeys = locations.keySet();
		int sampleSize = 0;
		List<MutationLocation> mutationsLocation = null;
		for (MutationType mutationKey : mutationKeys) {
			mutationsLocation = locations.get(mutationKey);
			sampleSize = TargetPopulation.calculateSampleSize(mutationsLocation.size(), confidenceLevel, marginError);
			newListLocations.put(mutationKey, IndividualConfidenceInterval(mutationsLocation, sampleSize));
		}
		return newListLocations;
	}

	private List<MutationLocation> IndividualConfidenceInterval(List<MutationLocation> mutants, int sampleSize) {
		List<MutationLocation> newMutants = new LinkedList<MutationLocation>();

		// TODO Verificar o(n)
		MutationLocation mutationLocation = null;
		int size = sampleSize - 1;
		for (int i = 0; i < sampleSize; i++) {
			int random = (int) Math.floor(Math.random() * (size + 1));
			mutationLocation = mutants.remove(random);
			size--;
			newMutants.add(mutationLocation);
		}
		return newMutants;
	}

}
