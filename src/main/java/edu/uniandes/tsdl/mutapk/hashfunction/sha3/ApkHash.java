package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

public class ApkHash {
	
	private final String hashManifest;
	private final String hashSmali;
	private final String hashResource;
	
	public static class Builder{
		private final String hashManifest;
		private final String hashSmali;
		private final String hashResource;
		
		public Builder(String hashManifest, String hashSmali, String hashResource) {
			this.hashManifest = hashManifest;
			this.hashSmali = hashSmali;
			this.hashResource = hashResource;
		}
		
		public ApkHash build() {
			return new ApkHash(this);
		}
	}
	
	private ApkHash(Builder builder) {
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
			ApkHash apkHash = (ApkHash) o;
			return hashManifest == apkHash.hashManifest && hashSmali == apkHash.hashSmali && hashResource == apkHash.hashResource;
		}
		
	}
	
	@Override
	public int hashCode() {
		int result = hashManifest.hashCode();
		result = 31*result + hashSmali.hashCode();
		result = 31*result + hashResource.hashCode();
		return result;
	}

}
