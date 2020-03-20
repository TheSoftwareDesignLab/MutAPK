package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

public class ApkHashSeparator {

	private int id = -1;
	private final String hashManifest;
	private final String hashSmali;
	private final String hashResource;
	private final int mutanteId; 

	public static class Builder {
		private final String hashManifest;
		private final String hashSmali;
		private final String hashResource;
		private final int mutanteId; 

		public Builder(String hashManifest, String hashSmali, String hashResource, int mutanteId) {
			this.hashManifest = hashManifest;
			this.hashSmali = hashSmali;
			this.hashResource = hashResource;
			this.mutanteId = mutanteId;
		}

		public ApkHashSeparator build() {
			return new ApkHashSeparator(this);
		}
	}

	private ApkHashSeparator(Builder builder) {
		this.hashManifest = builder.hashManifest;
		this.hashSmali = builder.hashSmali;
		this.hashResource = builder.hashResource;
		this.mutanteId = builder.mutanteId;
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

	public int getMutanteId() {
		return mutanteId;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if(o == null){
			return false;
		}
		if (!(o instanceof ApkHashSeparator)) {
			return false;
		}
		ApkHashSeparator apkHashseparator = (ApkHashSeparator) o;
		return (hashManifest.equals(apkHashseparator.getHashManifest())
				&& hashSmali.equals(apkHashseparator.getHashSmali())
				&& hashResource.equals(apkHashseparator.getHashResource()));

	}

	@Override
	public int hashCode() {
		int hash = 7;
        hash = 31 * hash + hashManifest.hashCode();
        hash = 31 * hash + hashSmali.hashCode();
        hash = 31 * hash + hashResource.hashCode();
        return hash;
	}

	@Override
	public String toString() {
		return hashManifest + "||" + hashResource + "||" + hashSmali;
	}

}
