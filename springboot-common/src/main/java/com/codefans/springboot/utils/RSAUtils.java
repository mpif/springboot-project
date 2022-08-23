package com.codefans.springboot.utils;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guanlichang@kingdee.com on 2019/04/22.
 **/
public class RSAUtils {
	public static final String CHAR_ENCODING = "UTF-8";

	public static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
	public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String RSA_KEY_ALGORITHM = "RSA";
	public static final String SIGN_ALGORITHM = "SHA1WithRSA";

	private static int KEYSIZE = 1024;

	public static Map<String, String> generateKeyPair() throws Exception {


		SecureRandom sr = new SecureRandom();

		KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);

		kpg.initialize(KEYSIZE, sr);

		KeyPair kp = kpg.generateKeyPair();

		Key publicKey = kp.getPublic();
		byte[] publicKeyBytes = publicKey.getEncoded();
		String pub = new String(Base64.getEncoder().encode(publicKeyBytes), CHAR_ENCODING);

		Key privateKey = kp.getPrivate();
		byte[] privateKeyBytes = privateKey.getEncoded();
		String pri = new String(Base64.getEncoder().encode(privateKeyBytes), CHAR_ENCODING);

		Map<String, String> map = new HashMap<String, String>();
		map.put("publicKey", pub);
		map.put("privateKey", pri);
		RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
		BigInteger bint = rsp.getModulus();
		byte[] b = bint.toByteArray();
		byte[] deBase64Value = Base64.getEncoder().encode(b);
		String retValue = new String(deBase64Value);
		map.put("modulus", retValue);
		return map;
	}


	public static String encrypt(String source, String publicKey) throws Exception {

		Key key = getPublicKey(publicKey);


		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();


		byte[] b1 = cipher.doFinal(b);

		return new String(Base64.getEncoder().encode(b1), CHAR_ENCODING);
	}


	public static String decrypt(String cryptograph, String privateKey) throws Exception {

		Key key = getPrivateKey(privateKey);


		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] b1 = Base64.getDecoder().decode(cryptograph.getBytes());


		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}


	public static PublicKey getPublicKey(String key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}


	public static PrivateKey getPrivateKey(String key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public static String sign(String content, String privateKey) throws Exception {
		String charset = CHAR_ENCODING;
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes()));
		KeyFactory keyf = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		PrivateKey priKey = keyf.generatePrivate(priPKCS8);

		Signature signature = Signature.getInstance(SIGN_ALGORITHM);
		signature.initSign(priKey);
		signature.update(content.getBytes(charset));
		byte[] signed = signature.sign();

		return new String(Base64.getEncoder().encode(signed));
	}

	public static boolean checkSign(String content, String sign, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
		byte[] encodedKey = Base64.getDecoder().decode(publicKey);

		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		Signature signature = Signature.getInstance(SIGN_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(content.getBytes(CHAR_ENCODING));

		boolean bverify = signature.verify(Base64.getDecoder().decode(sign));
		return bverify;
	}

	public static void main(String[] args) throws Exception {
	    String content = "hello";
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIDIqD2VVAXOsYGq2XvqN/R7Tg7Kx5EvO+NqJ0FBCZtPYHH1lrRIcL2gYnN2gDLKcu5Dxc7oriaGn2ThArnNw7Rrja4y3uacA2YQeymkgvJSwmvp0KEjFaUz2s15EoR5eznFn9jFftH6JIKp3TiqaeSqwLRdDO9Pb73/AoFB3ZD3AgMBAAECgYATMtax55MREpEvzXKCoJYa4k7usZgysqHw8yBLABEYkCrGkmAXHD2CZ2iF4gKWj8u2bXANum5L3AHjHhAz9tLX2kTbO6tAXzcVhHqvI74hpBLCgpmcDJNDUKZLrZ2GbWL/TGHyToF9ICoeU9DB5bSMjcdoVUhkn7E2o6eYuSir0QJBAMpGVxVFv1btOzxGPGWz2Ufvbc2lA9DNK/jIVWC6595b0t4gLzUlPCFi0xnk8rMll55SmCNIxv6dqt8ce1Wk5XUCQQCi/U37u1ISom/pHHakEz2rEbTdOnsLInuJ882s4TocydvIzxc8gbzK/mkhoND8LmOFl5zCAETSbFjn3v2s4RM7AkAdhL5YoGEX8m7ekPFndiOTV+GSTS6NXiO4nqO3Bg+oZ4YSXGgHPXPU+3MLKFoywp2pwSvqmkhQB/pTrPhiuzmxAkBLbN1BuRXp+bHANqPQmfbXy4HfPsjiBjsVQ/XSIwg5elBzRSKDpaYwajtMjJcpnG4BzH+GZ4lxtyINa2wJLL8xAkEAlmjpJS/EBpCXWrHVxbtZbhLEVuz5Lw+31avLKEqD0UbPypfLtty2ZdHNUDMIk08NjSgP6VDZ13yCuftKZHb56A==";
//		System.out.println(sign(content, getPrivateKey(privateKey).toString()));
		System.out.println(sign(content, privateKey));

//		Map<String, String> keyPair = generateKeyPair();
//		System.out.println("privateKey=" + keyPair.get("privateKey"));
//		System.out.println("publicKey=" + keyPair.get("publicKey"));
	}

}
