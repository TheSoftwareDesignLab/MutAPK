package edu.uniandes.tsdl.mutapk.selector;

import java.util.ArrayList;
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
		SelectorConfidenceInterval selectorConfidenceInterval = (SelectorConfidenceInterval) selectorType;
		int confidenceLevel = selectorConfidenceInterval.confidenceLevel();
		int marginError = selectorConfidenceInterval.marginError();
		if(selectorConfidenceInterval.isCIIndividual()) {
			HashMap<MutationType, List<MutationLocation>> newListLocations = getNewListLocationsIndividual(locations,
					confidenceLevel, marginError);
			return MutationLocationListBuilder.buildList(newListLocations);
		}else {
			int totalMutants = selectorConfidenceInterval.getTotalMutants();
			int allSampleSize = TargetPopulation.calculateSampleSize(totalMutants, confidenceLevel, marginError);
			
			HashMap<MutationType, List<MutationLocation>> newListLocations = getNewListLocationAll(locations,
					allSampleSize, totalMutants);
			return MutationLocationListBuilder.buildList(newListLocations);
		}
	}

	private HashMap<MutationType, List<MutationLocation>> getNewListLocationAll(
			HashMap<MutationType, List<MutationLocation>> locations, int allSampleSize, int totalMutants) {
		HashMap<MutationType, List<MutationLocation>> newListLocations = new HashMap<MutationType, List<MutationLocation>>();
		Set<MutationType> mutationKeys = locations.keySet();
		List<MutationLocation> mutationsLocation = null;
		List<MutationLocation> mutationsLocationCopy = null;
		int individualSampleSize = 0;
		int totalSampleSize = 0;
		System.out.println("Individual Sample Size		    Mutation Operator");
		for (MutationType mutationKey : mutationKeys) {
			mutationsLocation = locations.get(mutationKey);
			individualSampleSize =  (int) Math.ceil((double) (allSampleSize*mutationsLocation.size())/ totalMutants);
			totalSampleSize += individualSampleSize;
			mutationsLocationCopy = new ArrayList<MutationLocation>(mutationsLocation);
			List<MutationLocation> mutations = IndividualConfidenceInterval(mutationsLocationCopy, individualSampleSize);
			newListLocations.put(mutationKey, mutations);
			System.out.println(mutations.size() + "				" + mutationKey);
		}
		System.out.println("Total Sample size  " + totalSampleSize);
		return newListLocations;
	}

	private HashMap<MutationType, List<MutationLocation>> getNewListLocationsIndividual(
			HashMap<MutationType, List<MutationLocation>> locations, int confidenceLevel, int marginError) {
		HashMap<MutationType, List<MutationLocation>> newListLocations = new HashMap<MutationType, List<MutationLocation>>();
		Set<MutationType> mutationKeys = locations.keySet();
		int totalSampleSize = 0;
		int sampleSize = 0;
		List<MutationLocation> mutationsLocation = null;
		System.out.println("Individual Sample Size		    Mutation Operator");
		for (MutationType mutationKey : mutationKeys) {
			mutationsLocation = locations.get(mutationKey);
			sampleSize = TargetPopulation.calculateSampleSize(mutationsLocation.size(), confidenceLevel, marginError);
			totalSampleSize += sampleSize;
			List<MutationLocation> mutations = IndividualConfidenceInterval(mutationsLocation, sampleSize);
			newListLocations.put(mutationKey, mutations);
			System.out.println(mutations.size() + "		                     " + mutationKey);
		}
		System.out.println("Total sample size: " + totalSampleSize);
		return newListLocations;
	}

	private List<MutationLocation> IndividualConfidenceInterval(List<MutationLocation> mutants, int sampleSize) {
		List<MutationLocation> newMutants = new LinkedList<MutationLocation>();
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
