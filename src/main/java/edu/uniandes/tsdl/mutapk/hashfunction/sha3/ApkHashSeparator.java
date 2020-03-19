package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ApkHashSeparator {

	@EqualsAndHashCode.Exclude private int id = -1;
	private final String hashManifest;
	private final String hashSmali;
	private final String hashResource;

	public static class Builder {
		private final String hashManifest;
		private final String hashSmali;
		private final String hashResource;

		public Builder(String hashManifest, String hashsmali, String hashResource) {
			this.hashManifest = hashManifest;
			this.hashSmali = hashsmali;
			this.hashResource = hashResource;
		}

		public ApkHashSeparator build() {
			return new ApkHashSeparator(this);
		}
	}

	private ApkHashSeparator(Builder builder) {
		this.hashManifest = builder.hashManifest;
		this.hashSmali = builder.hashSmali;
		this.hashResource = builder.hashResource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getHashManifest() {
		return hashManifest;
	}

	public String getHashSmali() {
		return hashSmali;
	}

	public String getHashResource() {
		return hashResource;
	}

//	@Override
//	public boolean equals(Object o) {
//		if (o == this) {
//			return true;
//		} else if (!(o instanceof ApkHashSeparator)) {
//			return false;
//		} else {
//			ApkHashSeparator apkHashseparator = (ApkHashSeparator) o;
////			boolean isEqualManifest = hashManifest.equals(apkHashseparator.getHashManifest());
////			boolean isEqualSmali = isEqual(apkHashseparator.getHashSmali(), hashSmali);
////			boolean isEqualResource = isEqual(apkHashseparator.getHashResource(), hashResource);
//			return hashManifest.equals(apkHashseparator.getHashManifest())
//					&& hashSmali.equals(apkHashseparator.getHashSmali())
//					&& hashResource.equals(apkHashseparator.getHashResource());
//		}
//
//	}
//
////	private boolean isEqual(String hash, String compare) {
////		if (hash.length() != compare.length()) {
////			return false;
////		}
////		String[] hashes = hash.split("|");
////		String[] compares = compare.split("|");
////		for (int i = 0; i < hashes.length; i++) {
////			if (!compares[i].equals(hashes[i])) {
////				return false;
////			}
////		}
////		return true;
////	}
//
//	@Override
//	public int hashCode() {
//		int result = hashManifest.hashCode();
//		result = 31 * result + hashSmali.hashCode();
//		result = 31 * result + hashResource.hashCode();
//		return result;
//	}
	
	
	@Override
	public String toString() {
		return hashManifest + "|" + hashResource + "|" + hashSmali;
	}

}
