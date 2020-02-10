package edu.uniandes.tsdl.mutapk.selector;

import java.util.HashMap;
import java.util.List;

import edu.uniandes.tsdl.mutapk.model.MutationType;
import edu.uniandes.tsdl.mutapk.model.location.MutationLocation;

public interface Selector {

	public List<MutationLocation> mutantSelector(HashMap<MutationType, List<MutationLocation>> locations,
			SelectorType selectorType);

}
