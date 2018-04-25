package uniandes.tsdl.mutapk.operators;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import uniandes.tsdl.mutapk.detectors.MutationLocationDetector;
import uniandes.tsdl.mutapk.detectors.xml.ActivityNotDefinedDetector;
import uniandes.tsdl.mutapk.model.MutationType;

public class OperatorBundle {

	private static final String PROPERTY_FILE_NAME = "operators";
	private ResourceBundle bundle;

//	public enum TextBasedOperator {
//		ActivityNotDefined(1), InvalidActivityName(3), InvalidColor(3), InvalidLabel(28), MissingPermission(9), WrongStringResource(10), SDKVersion(12), WrongMainActivity(8);
//
//		public int id;
//
//		TextBasedOperator(int id) {
//			this.id = id;
//		}
//	}

	public OperatorBundle(String propertyDir) {
		init(propertyDir);
	}

	
	public boolean isOperatorSelected(String id) {
		return bundle.containsKey(id);
	}


	public List<MutationLocationDetector> getTextBasedDetectors() {
		List<MutationLocationDetector> textBasedDetectors = new ArrayList<>();
		
		
		if(bundle.containsKey(MutationType.ACTIVITY_NOT_DEFINED.getId()+"")) {
			textBasedDetectors.add(new ActivityNotDefinedDetector());
		}

		return textBasedDetectors;
	}
	
	public String printSelectedOperators() {
		
		Set<String> ids = bundle.keySet();
		String selectedOperators = "Selected Operators: "+ids.size()+"\n";

		for (String id : ids) {
			selectedOperators += id+" "+bundle.getString(id)+"\n";
		}
		selectedOperators += "------------\n";
		
		return selectedOperators;
	}



	private void init(String propertyDir) {
		File file = new File(propertyDir);
		URL url = null;

		try {
			url = file.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		URL[] urls = {url};
		ClassLoader loader = new URLClassLoader(urls);
		bundle = ResourceBundle.getBundle(PROPERTY_FILE_NAME, Locale.getDefault(), loader);
	}

}
