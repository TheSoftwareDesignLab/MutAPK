package edu.uniandes.tsdl.mutapk.selector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.uniandes.tsdl.mutapk.detectors.MutationLocationListBuilder;
import edu.uniandes.tsdl.mutapk.exception.MutAPKException;
import edu.uniandes.tsdl.mutapk.model.MutationType;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;

public class SelectorAmountMutantsMethod implements InterfaceSelector{

	public SelectorAmountMutantsMethod() {}

	public List<MutationLocation> mutantSelector(HashMap<MutationType, List<MutationLocation>> locations, SelectorType selectorType) throws MutAPKException {

		HashMap<MutationType, List<MutationLocation>> newLocations = new HashMap<MutationType, List<MutationLocation>>();
		HashMap<MutationType, List<MutationLocation>> tempLocations = locations;
		SelectorAmountMutants selectorAmountMutants = (SelectorAmountMutants) selectorType;
		int newAmountMutants = selectorAmountMutants.getAmountMutants();
		List<MutationLocation> mutationLocationList = MutationLocationListBuilder.buildList(tempLocations);

		// Check if the amount of PFLocations is lower than the requested by the user
		if(mutationLocationList.size() < newAmountMutants) {
			throw new MutAPKException("The total of mutants need to be greater than the amount of mutants asked");
		}
		System.out.println("");

		for (MutationType key : tempLocations.keySet()) {
			if (tempLocations.get(key).size() > 0) {
				int selectedMutantNumber = (int) Math.random() * tempLocations.get(key).size();
				MutationLocation selectedMutant = tempLocations.get(key).get(selectedMutantNumber);
				ArrayList<MutationLocation> temp = new ArrayList<MutationLocation>();
				temp.add(selectedMutant);
				newLocations.put(key, temp);
				tempLocations.get(key).remove(selectedMutantNumber);
				newAmountMutants--;
			}
		}
		List<MutationLocation> newMutationLocationList = MutationLocationListBuilder.buildList(newLocations);

		for (int i = 0; i < newAmountMutants; i++) {
			int selectedMutantNumber = (int) Math.random() * mutationLocationList.size();
			MutationLocation selectedMutant = mutationLocationList.get(selectedMutantNumber);
			newMutationLocationList.add(selectedMutant);
			mutationLocationList.remove(selectedMutantNumber);
		}
		return newMutationLocationList;
	}

}
