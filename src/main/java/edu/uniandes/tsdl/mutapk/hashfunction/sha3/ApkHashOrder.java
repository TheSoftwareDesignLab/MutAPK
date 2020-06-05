package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
		boolean isDuplicate = apkHashesSeparator.containsKey(nuevoApkHashSeparator);
		if(isDuplicate) {
			ApkHashSeparator duplicate = apkHashesSeparator.get(nuevoApkHashSeparator);
			System.out.println(nuevoApkHashSeparator.getMutantId() + " HashCode: " + nuevoApkHashSeparator.hashCode() +  " duplicate: " +  duplicate.hashCode());
			if(duplicate != null && nuevoApkHashSeparator.equals(duplicate)) {
//				System.out.println("ES IGUAL: " + nuevoApkHashSeparator.equals(duplicate));
//				System.out.println("ES IGUAL Nuevo: " + nuevoApkHashSeparator.getMutanteId());
//				System.out.println("ES IGUAL Nuevo Manifest: " + nuevoApkHashSeparator.getMutanteId() + " " + nuevoApkHashSeparator.getHashManifest());
//				System.out.println("ES IGUAL Nuevo Smali: " + nuevoApkHashSeparator.getMutanteId() + " " + nuevoApkHashSeparator.getHashSmali());
//				System.out.println("ES IGUAL Nuevo Resource: " + nuevoApkHashSeparator.getMutanteId() + " " + nuevoApkHashSeparator.getHashResource());
//				System.out.println("ES IGUAL Nuevo: " + nuevoApkHashSeparator.getMutanteId() + " Viejo " + duplicate.getMutanteId());
//				System.out.println("ES IGUAL Nuevo Manifest: "  + nuevoApkHashSeparator.getMutanteId() + " Viejo " + duplicate.getMutanteId() + " " + nuevoApkHashSeparator.getHashManifest());
//				System.out.println("ES IGUAL Nuevo Smali: " + nuevoApkHashSeparator.getMutanteId() + " Viejo " + duplicate.getMutanteId() + " " + nuevoApkHashSeparator.getHashSmali());
//				System.out.println("ES IGUAL Nuevo Resource: " + nuevoApkHashSeparator.getMutanteId() + " Viejo " +  duplicate.getMutanteId() + " " + nuevoApkHashSeparator.getHashResource());
				return duplicate;
			}
		}
		System.out.println(nuevoApkHashSeparator.getMutantId() + " HashCode: " + nuevoApkHashSeparator.hashCode());
		nuevoApkHashSeparator.setId(id);
		apkHashesSeparator.put(nuevoApkHashSeparator, nuevoApkHashSeparator);
		id++;
		return null;
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized int getLength() {
		return apkHashesSeparator.size();
	}

}
