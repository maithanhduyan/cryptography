package com.crypto;

import java.util.Random;

/**
 * Alphanumericals are a combination of alphabetical and numerical characters,
 * and is used to describe the collection of Latin letters and Arabic digits or
 * a text constructed from this collection.
 *
 */
public class AlphanumericRandom {

	static Random rand = new Random();
	static StringBuffer sb;
	public static final String Alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String of() {
		sb = new StringBuffer();
		for (int i = 0; i < Alphanumeric.length(); i++) {
			sb.append(Alphanumeric.charAt(rand.nextInt(31)));
		}
		return sb.toString();
	}

	public static String of(int length) {
		sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(Alphanumeric.charAt(rand.nextInt(31)));
		}
		return sb.toString();
	}
}
