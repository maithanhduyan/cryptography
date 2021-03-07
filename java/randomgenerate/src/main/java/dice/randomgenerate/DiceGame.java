package dice.randomgenerate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class DiceGame {

	public static void main(String[] args) {

		while (true) {
			System.out.println("System");
			System.out.println("hash"+ SHA_256("hello"));
			byte[] bytes = "hello".getBytes();

			  // byte[] to string
			  String s = new String(bytes, StandardCharsets.UTF_8);
			System.out.println(bytes.toString());
			System.out.println(s);
			
			String serverseed = "581c42e8ee9b71b114bb6739a6c8ac7d6258eb843df5072df2c7baf32c48a50e";
			byte[] byteArr = hexStringToByteArray(serverseed);
			System.out.println("HEX: " + byteArr[0]);
			System.out.println("HASH HEX: " + DigestUtils.sha256Hex((serverseed).getBytes(StandardCharsets.UTF_8)));
			
			System.out.println("Byte[0] " +(serverseed).getBytes()[0]);
			
			
			
			delay(1000);
		}
	}
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

	static void delay(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static int calculateBetResult() {
		return 0;
	}

	public static byte[] SHA_256(String text) {
		MessageDigest digest;
		byte[] hash = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash;
	}
}
