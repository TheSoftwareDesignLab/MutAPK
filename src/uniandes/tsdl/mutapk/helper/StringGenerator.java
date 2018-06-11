package uniandes.tsdl.mutapk.helper;

import java.util.UUID;

public class StringGenerator {

	
	public static String generateRandomString(){
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
