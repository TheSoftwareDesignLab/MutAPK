package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

public class ApkHashSeparator {

	private final int id; 
	private final String hashManifest;
	private final String hashSmali;
	private final String hashResource;
	
	public static class Builder{
		private final int id; 
		private final String hashManifest;
		private final String hashSmali;
		private final String hashResource;
		
		public Builder(int id,String hashManifest, String hashsmali, String hashResource) {
			this.id = id;
			this.hashManifest = hashManifest;
			this.hashSmali = hashsmali;
			this.hashResource = hashResource;
		}
		
		
		public ApkHashSeparator build() {
			return new ApkHashSeparator(this);
		}
	}
	
	private ApkHashSeparator(Builder builder) {
		this.id = builder.id;
		this.hashManifest = builder.hashManifest;
		this.hashSmali = builder.hashSmali;
		this.hashResource = builder.hashResource;
	}
	
	public int getId() {
		return id;
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
		if(hash.length() != compare.length()) {
			return false;
		}
		String[] hashes = hash.split("|");
		String[] compares = compare.split("|");
 		for (int i = 0; i < hashes.length; i++) {
			if(!compares[i].equals(hashes[i])) {
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
