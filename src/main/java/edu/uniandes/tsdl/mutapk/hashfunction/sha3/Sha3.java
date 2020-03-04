package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

public class Sha3 {

	/**
	 * Static Factory Method
	 */
	private Sha3() {
	}

	public static String sha384File(final File file) throws IOException {
		final DigestSHA3 sha3 = new DigestSHA3(384);
		byte[] hash = hashFile(file, sha3);
		return hashToString(hash);
	}

	private static byte[] hashFile(final File file, DigestSHA3 sha3) throws FileNotFoundException, IOException {
		if (file.isFile()) {
			return fileHash(file);
		} else {
			File[] files = file.listFiles();
			for (File file2 : files) {
				byte[] hash = hashFile(file2, sha3);
				sha3.update(hash);
			}
			return sha3.digest();
		}
	}

	private static byte[] fileHash(final File file) throws FileNotFoundException, IOException {
		final DigestSHA3 sha3 = new DigestSHA3(384);
		InputStream fis = new FileInputStream(file);
		int n = 0;
		byte[] buffer = new byte[8192];
		while (n != -1) {
			n = fis.read(buffer);
			if (n > 0) {
				sha3.update(buffer, 0, n);
			}
		}
		byte[] hash = sha3.digest();
		fis.close();
		return hash;
	}

	public static String sha384FileSeparte(final File file) throws IOException {
		String hash = hashFileSeparator(file);
		return hash.substring(0, hash.length() - 1);
	}

	private static String hashFileSeparator(final File file) throws FileNotFoundException, IOException {
		if (file.isFile()) {
			String hash = fileHashSeparete(file) + "|";
			return hash;
		} else {
			File[] files = file.listFiles();
			String hash = "";
			for (File file2 : files) {
				hash += hashFileSeparator(file2);
			}
			return hash;
		}
	}

	private static String fileHashSeparete(final File file) throws FileNotFoundException, IOException {
		final DigestSHA3 sha3 = new DigestSHA3(384);
		InputStream fis = new FileInputStream(file);
		int n = 0;
		byte[] buffer = new byte[8192];
		while (n != -1) {
			n = fis.read(buffer);
			if (n > 0) {
				sha3.update(buffer, 0, n);
			}
		}
		byte[] hash = sha3.digest();
		fis.close();
		return hashToString(hash);
	}
	
	private static String hashToString(byte[] hash) {
		StringBuffer buff = new StringBuffer();
		for (byte b : hash) {
			buff.append(String.format("%02x", b & 0xFF));
		}
		return buff.toString();
	}

}
