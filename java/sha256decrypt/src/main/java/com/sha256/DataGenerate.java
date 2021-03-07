package com.sha256;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class DataGenerate {

    static Random rand = new Random();
    static final int SERVER_SEED_LENGTH = 64;
    public static final String HEX = "0123456789abcdef";

    public DataGenerate() {

    }

    /**
     * Create Random Server Seed String Length 32 characters 0->f
     */
    public static String randomServerSeed() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            sb.append(HEX.charAt(rand.nextInt(16)));
        }
        return sb.toString();
    }

    /**
     * Hash data to a hex string <br>
     * Testing at : <a href=
     * "https://emn178.github.io/online-tools/sha256.html">https://emn178.github.io/online-tools/sha256.html</a>
     */
    public static String hash(String serverSeed) {

        byte[] _b = hexToString(serverSeed);

        return DigestUtils.sha256Hex(_b);
    }

    /**
     * Convert Hex String to Byte Array
     */
    public static byte[] hexToString(String hex) {
        // TODO data validation here
        byte[] _b = new byte[hex.length() / 2];
        int i = 0;
        for (int n = 0; n < hex.length(); n += 2) {
            String _str = hex.substring(n, n + 2);
            int code = Integer.parseInt(_str, 16);
            _b[i] = (byte) code;
            i++;
        }
        return _b;
    }

    public static char[] strToCharArr(String str) {
        char[] c = null;
        try {
            if (str.length() > 0) {
                for (int i = 0; i < str.length(); i++) {
                    c[i] = str.charAt(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    public static double[] strToDoubleArr(String str) {
        int lenght = str.length();
        double[] output = new double[lenght];
        for (int n = 0; n < lenght; n++) {
            output[n] = (double) str.charAt(n);
        }
        return output;
    }

    /**
     * Convert Hex String To Binary String To Double Array
     */
    public static double[] hexToDoubleArr(String hex) {
        double[] output = new double[hex.length() * 8];
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < hex.length(); i++) {
                String s = stringToBinary("" + hex.charAt(i));
                sb.append(s);
            }
            //System.out.println(sb.toString());
            for (int i = 0; i < output.length; i++) {
                output[i] = Integer.parseInt(sb.toString().charAt(i) + "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return output;
    }

    public static String stringToBinary(String str) {
        byte[] ba = str.getBytes(StandardCharsets.UTF_8);
        StringBuilder output = new StringBuilder();
        for (byte b : ba) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                output.append((val & 128) == 0 ? 0 : 1);      // 128 = 1000 0000
                val <<= 1;
            }
        }
        return output.toString();
    }

    public static String binaryToHex(String binary) {
        byte[] array = ByteBuffer.allocate(4).putInt( // 4 bytes byte[]
                Integer.parseInt(binary, 2)
        ).array();
        String result = new String(array,StandardCharsets.UTF_8);
        return result;
    }
}
