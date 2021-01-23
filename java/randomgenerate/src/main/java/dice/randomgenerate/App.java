package dice.randomgenerate;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// Server Seed:
		// 581c42e8ee9b71b114bb6739a6c8ac7d6258eb843df5072df2c7baf32c48a50e
		// Server Seed Hash(SHA-256):
		// b626c8cf4f3fbaa122e1e4ab886f21787512a35ff931fdef71d5f88ac63a85e0
		String str = "581c42e8ee9b71b114bb6739a6c8ac7d6258eb843df5072df2c7baf32c48a50e";
		byte[] sha156Encrypt = SHA_256(str);
		String hex_output = toHexString(sha156Encrypt);
		String hex_output2 = encodeHexString(sha156Encrypt);
		System.out.println("HEX: " + hex_output);
		System.out.println("HEX2: " + hex_output2);
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

	public static String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);
		System.out.println("number:" + number);
		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

	public static String encodeHexString(byte[] byteArray) {
		StringBuffer hexStringBuffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			hexStringBuffer.append(byteToHex(byteArray[i]));
		}
		return hexStringBuffer.toString();
	}

	public static byte hexToByte(String hexString) {
		int firstDigit = toDigit(hexString.charAt(0));
		int secondDigit = toDigit(hexString.charAt(1));
		return (byte) ((firstDigit << 4) + secondDigit);
	}

	private static int toDigit(char hexChar) {
		int digit = Character.digit(hexChar, 16);
		if (digit == -1) {
			throw new IllegalArgumentException("Invalid Hexadecimal Character: " + hexChar);
		}
		return digit;
	}

	public static String byteToHex(byte num) {
		char[] hexDigits = new char[2];
		hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
		hexDigits[1] = Character.forDigit((num & 0xF), 16);
		return new String(hexDigits);
	}
}
