package edu.uniandes.tsdl.mutapk.hashfunction.sha3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.StringJoiner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;


public class Sha3 {

	/**
	 * Static Factory Method
	 */
	private Sha3() {
	}

	public static String sha512File(final File file)
			throws FileNotFoundException, IOException {
		final DigestSHA3 sha3 = new DigestSHA3(512);
		if (file.isFile()) {
			sha3.update(fileHash(file));
		} else {
			Collection<File> files = FileUtils.listFiles(new File(file.getAbsolutePath()), TrueFileFilter.INSTANCE,
					TrueFileFilter.INSTANCE);
			for (File fileHash : files) {
				sha3.update(fileHash(fileHash));
			}
		}
		return hashToString(sha3.digest());
	}

	public static String sha512FileSeparte(final File file)
			throws FileNotFoundException, IOException {
		if (file.isFile()) {
			return hashToString(fileHash(file));
		} else {
			StringJoiner hashes = new StringJoiner("|");
			Collection<File> files = FileUtils.listFiles(new File(file.getAbsolutePath()), TrueFileFilter.INSTANCE,
					TrueFileFilter.INSTANCE);
			for (File fileHash : files) {
				String string = hashToString(fileHash(fileHash)); 
				hashes.add(string);
			}
			return hashes.toString();
		}
	}

	private static byte[] fileHash(final File file) throws FileNotFoundException, IOException {
		final DigestSHA3 sha3 = new DigestSHA3(512);
		BufferedInputStream bis =new BufferedInputStream(new FileInputStream(file));
		int n = 0;
		byte[] buffer = new byte[8192];
		while ((n = bis.read(buffer)) > 0) {
			sha3.update(buffer, 0, n);
	    }
	    bis.close();
		byte[] hash = sha3.digest();
		bis.close();
		return hash;
	}

	private static String hashToString(byte[] hash) {
		StringBuffer buff = new StringBuffer();
		for (byte b : hash) {
			buff.append(String.format("%02x", b & 0xFF));
		}
		return buff.toString();
	}

}
