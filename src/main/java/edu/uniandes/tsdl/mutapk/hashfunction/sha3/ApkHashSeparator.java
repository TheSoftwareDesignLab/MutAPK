package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ApkHashSeparator {

	@EqualsAndHashCode.Exclude private int id = -1;
	private final String hashManifest;
	private final String hashSmali;
	private final String hashResource;
	@EqualsAndHashCode.Exclude private final int mutanteId; 

	public static class Builder {
		private final String hashManifest;
		private final String hashSmali;
		private final String hashResource;
		private final int mutanteId; 

		public Builder(String hashManifest, String hashsmali, String hashResource, int mutanteId) {
			this.hashManifest = hashManifest;
			this.hashSmali = hashsmali;
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
	public String toString() {
		return hashManifest + "|" + hashResource + "|" + hashSmali;
	}

}
