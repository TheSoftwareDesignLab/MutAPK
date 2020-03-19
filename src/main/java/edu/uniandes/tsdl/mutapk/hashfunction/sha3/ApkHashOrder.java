package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

import java.util.HashSet;
import java.util.Set;

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
	private Set<ApkHashSeparator> apkHashesSeparator = new HashSet<ApkHashSeparator>();

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
		boolean isDuplicate = apkHashesSeparator.contains(nuevoApkHashSeparator);
		if(isDuplicate) {
			for(ApkHashSeparator apk : apkHashesSeparator) {
				if(apk.equals(nuevoApkHashSeparator)) {
					return apk;
				}
			}
		}else {
			nuevoApkHashSeparator.setId(id);
			apkHashesSeparator.add(nuevoApkHashSeparator);
			id++;
		}
		return null;
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized int getLength() {
		return apkHashesSeparator.size();
	}

}
