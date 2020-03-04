package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

public class ApkHashSeparator {

	private final String hashManifest;
	private final String hashSmali;
	private final String hashResource;
	
	public static class Builder{
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

	public String getHashManifest() {
		return hashManifest;
	}

	public String getHashSmali() {
		return hashSmali;
	}

	public String getHashResource() {
		return hashResource;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		} else if(!(o instanceof ApkHash)) {
			return false;
		}else {
			ApkHashSeparator apkHashseparator = (ApkHashSeparator) o;
			boolean isEqualManifest = hashManifest == apkHashseparator.getHashManifest();
			boolean isEqualSmali = isEqual(apkHashseparator.getHashSmali(), hashSmali); 
			boolean isEqualResource = isEqual(apkHashseparator.getHashResource(), hashResource); 
			return  isEqualManifest && isEqualSmali && isEqualResource;
		}
		
	}
	
	private boolean isEqual(String hash, String compare) {
		String[] hashes = hash.split("|");
		for (int i = 0; i < hashes.length; i++) {
			if(compare.equals(hashes[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = hashManifest.hashCode();
		result = 31*result + hashSmali.hashCode();
		result = 31*result + hashResource.hashCode();
		return result;
	}

}
