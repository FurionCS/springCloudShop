package com.spring.common.model.util.tools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;


/**
 * 加密，解密工具类
 * @author Mr.Cheng
 *
 */
public class SecurityUtil {
	private static	Logger logger =Logger.getLogger(SecurityUtil.class);  
	/**
	 * md5加密
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String password) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, md.digest()).toString(16);
	}
	/**
	 * md5,加入盐值 
	 * @param salt
	 * @param password
	 * @return type 位小写
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String salt,String password,int type) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("MD5");
		md.update(salt.getBytes());
		md.update(password.getBytes());
		return new BigInteger(1, md.digest()).toString(type);
	}
	/**
	 * md5加密
	 * @param sourceStr   //原字符串
	 * @param type        // 16位还是32位
	 * @return  大写
	 */
	public static String md5(String sourceStr,int type) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            if(type==32){
            	result = buf.toString();
            }else{
            	result.substring(8, 24).toUpperCase();//最后转化成大写
            }
        } catch (NoSuchAlgorithmException e) {
        	logger.error(e.getMessage());
        }
        return result;
    }
	/**
	 * aes加密
	 * @param data   //原字符串
	 * @param key    //秘钥
	 * @param iv     //初始化向量,不指定每次加密结果不一致，iv为16位
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data,String key,String iv) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return new sun.misc.BASE64Encoder().encode(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * aes解密
	 * @param data  //加密过的数据
	 * @param key  //秘钥
	 * @param iv   //初始化向量,不指定每次加密结果不一致，iv为16位
	 * @return
	 * @throws Exception
	 */
	public static String desEncrypt(String data,String key,String iv) throws Exception {
		try
		{
			byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(data);
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
