package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

import java.util.HashMap;
import java.util.Map;

/**
 * University of Los Andes
 * 
 * The class is going to be Singleton and it need to be thread safe.
 *
 */
public final class ApkHashOrder {

	/**
	 * The volatile word is used to ensure that the class is save in the main
	 * memory. Also, it ensure visibility change to variables across threads.
	 */
	private static volatile ApkHashOrder instance;

	private int id = 0;
	private Map<ApkHashSeparator, ApkHashSeparator> apkHashesSeparator = new HashMap<ApkHashSeparator, ApkHashSeparator>();

	/**
	 * Enforce private constructor
	 */
	private ApkHashOrder() {	}

	public static ApkHashOrder getInstance() {
		if (instance == null) {
			synchronized (ApkHashOrder.class) {
				if (instance == null) {
					instance = new ApkHashOrder();
				}
			}
		}
		return instance;
	}

	public synchronized ApkHashSeparator setApkHashSeparator(ApkHashSeparator nuevoApkHashSeparator) {
		boolean isInCollection = apkHashesSeparator.containsKey(nuevoApkHashSeparator);
		ApkHashSeparator apkHashSeparatorDuplicate = null;
		if (isInCollection) {
			apkHashSeparatorDuplicate = apkHashesSeparator.get(nuevoApkHashSeparator);
		} else {
			nuevoApkHashSeparator.setId(id);
			apkHashesSeparator.put(nuevoApkHashSeparator, nuevoApkHashSeparator);
			id++;
		}
		return apkHashSeparatorDuplicate;
	}
	
	public synchronized int getId() {
		return id;
	}

	public synchronized int getLength() {
		return apkHashesSeparator.size();
	}

}
