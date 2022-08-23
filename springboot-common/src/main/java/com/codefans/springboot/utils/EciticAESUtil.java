//package com.codefans.springboot.utils;
//
//import io.micrometer.core.instrument.util.StringUtils;
//import org.apache.commons.codec.DecoderException;
//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import javax.crypto.*;
//import javax.crypto.spec.SecretKeySpec;
//
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//
///**
// *
// * @author zhuhongyu808
// *
// */
//public class EciticAESUtil {
//
//	private static final Log LOGGER = LogFactory.getLog(EciticAESUtil.class);
//
//	/**
//	 * 密钥算法
//	 */
//	private static final String KEY_ALGORITHM = "AES";
//
//	/**
//	 * 加密解密算法/工作模式/填充方式
//	 */
//	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
//
//	//TODO 配置文件配置这个密码
//	public static final String AES_KEY_UNION =
//			"b8ddae4ec60a253307a2ddb647302265";
//
//	private EciticAESUtil(){
//
//	}
//
//	/**
//	 * AES加密
//	 * @param data 待加密数据
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 * @throws Exception
//	 */
//	public static String aesEncrypt(String data) throws UnsupportedEncodingException{
//
//
//		if (StringUtils.isBlank(AES_KEY_UNION)) {
//			LOGGER.error("AES加密密钥为空");
//			throw new IllegalArgumentException("AES加密密钥不能为空");
//		}
//		if (StringUtils.isBlank(data)) {
//			LOGGER.error("AES加密内容为空");
//			throw new IllegalArgumentException("AES加密内容不能为空");
//		}
//		byte[] output = null;
//		Key skeySpec;
//		try {
//			skeySpec = toKey(AES_KEY_UNION);
//			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//			output = cipher.doFinal(data.getBytes("UTF-8"));
//		} catch (Exception e) {
//			LOGGER.error("EciticAESUtil aesEncrypt error:" + e);
//		}
//
//		return new String(Hex.encodeHex(output));
//	}
//
//	/**
//	 * 转换密钥
//	 * @param key 十六进制编码格式密钥
//	 * @return
//	 * @throws Exception
//	 */
//	public static Key toKey(String key) {
//		byte[] tmpKey = null;
//		try {
//			tmpKey = Hex.decodeHex(key.toCharArray());
//		} catch (DecoderException e) {
//			LOGGER.error("EciticAESUtil toKey error:" + e);
//		}
//		SecretKey secretKey = new SecretKeySpec(tmpKey, KEY_ALGORITHM);
//		return secretKey;
//	}
//
//	/**
//	 * AES解密
//	 * @param data 待解密数据
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 * @throws Exception
//	 */
//	public static String aesDecrypt(String data) throws UnsupportedEncodingException{
//		if (StringUtils.isBlank(AES_KEY_UNION)) {
//			LOGGER.error("AES解密密钥为空");
//			throw new IllegalArgumentException("AES解密密钥不能为空");
//		}
//		if (StringUtils.isBlank(data)) {
//			LOGGER.error("AES解密内容为空");
//			throw new IllegalArgumentException("AES解密内容不能为空");
//		}
//		Key skeySpec = toKey(AES_KEY_UNION);
//		Cipher cipher;
//		String decryptData = "";
//		try {
//			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//
//			byte[] output = cipher.doFinal(Hex.decodeHex(data.toCharArray()));
//
//			if(null != output){
//				decryptData = new String (new String(output).getBytes("UTF-8"),"UTF-8");
//
//			}
//		} catch (NoSuchAlgorithmException e) {
//			LOGGER.error("EciticAESUtil aesDecrypt NoSuchAlgorithmException:" + e);
//		} catch (NoSuchPaddingException e) {
//			LOGGER.error("EciticAESUtil aesDecrypt NoSuchPaddingException:" + e);
//		} catch (InvalidKeyException e) {
//			LOGGER.error("EciticAESUtil aesDecrypt InvalidKeyException:" + e);
//		} catch (IllegalBlockSizeException e) {
//			LOGGER.error("EciticAESUtil aesDecrypt IllegalBlockSizeException:" + e);
//		} catch (BadPaddingException e) {
//			LOGGER.error("EciticAESUtil aesDecrypt BadPaddingException:" + e);
//		} catch (DecoderException e) {
//			LOGGER.error("EciticAESUtil aesDecrypt DecoderException:" + e);
//		}
//
//		return decryptData;
//	}
//}
