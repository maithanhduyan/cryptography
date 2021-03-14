package com.sha256;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHA256Test {

	static Logger log = LoggerFactory.getLogger(SHA256Test.class);

	public static void main(String[] args) {

		String inputText = "abc";
		SHA256 sha256;
		log.info("" + SHA256.hash(inputText.getBytes()));
	}

}
