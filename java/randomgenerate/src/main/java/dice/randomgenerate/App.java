package dice.randomgenerate;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Sha2Crypt;

import com.crypto.AlphanumericRandom;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

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
		// e520bc83603e11410d0fb07abfe2d44f224dd6f0303cbf37aab1d62101ddc077
		String str = "581c42e8ee9b71b114bb6739a6c8ac7d6258eb843df5072df2c7baf32c48a50e";

		// Apache Commons Codec
		String hash = Sha2Crypt.sha256Crypt(Hex.encodeHexString(str.getBytes()).getBytes());
		System.out.println("Apache Commons Codec SHA-256 Crypt: " + hash);
		System.out.println("Apache Commons Codec SHA-256 HASH: " + DigestUtils.sha256Hex(str));

		// Google Guava
		String hashoutput = Hashing.sha256().newHasher().putString(str, Charsets.UTF_8).hash().toString();

		System.out.println("Google Guava SHA-256 HASH: " + hashoutput);
		System.out.println("" + AlphanumericRandom.of(32));
		Thread worker = new Thread(new Runnable() {

			public void run() {
				System.out.println("HASH: "+ DigestUtils.sha256Hex(AlphanumericRandom.of(32)));
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"Worker");

		while (true) {
			worker.run();
			System.out.println("Thread Name: "+worker.getName()+" ID:" + worker.getId()+"Priority :" +worker.getPriority());
		}
	}
}
