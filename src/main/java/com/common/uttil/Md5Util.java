package com.common.uttil;

import java.security.MessageDigest;
import java.util.Random;

public class Md5Util {
	
	private static final char HEX_DIGITS[] = {
	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	        'a', 'b', 'c', 'd', 'e', 'f'
	    };

	/**
	 * @Title: thirtyTwo
	 * @Description: 32位MD5加密
	 * @param content
	 * @return String
	 */
	public static String toMd5(String content) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] resultByte = md.digest(content.getBytes("UTF-8"));
			StringBuffer buf = new StringBuffer("");
			int i;
			for (int offset = 0; offset < resultByte.length; offset++) {
				i = resultByte[offset];
				i = i & 0xff;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getFormattedText(byte bytes[]) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(int j = 0; j < bytes.length; j++)
        {
            buf.append(HEX_DIGITS[bytes[j] >> 4 & 15]);
            buf.append(HEX_DIGITS[bytes[j] & 15]);
        }
        return buf.toString();
    }
	//md5
	public static synchronized String EncoderByMd5(String str,String charSet){
        try{
			MessageDigest md5=MessageDigest.getInstance("MD5");
			md5.update(str.getBytes(charSet));
			byte[] digest = md5.digest();
			return getFormattedText(digest);
        }catch(Exception e){}
        return null;
    }
	/**
	 * 生成盐值
	 * 
	 * @return
	 */
	public static String getSalt() {
		Random r = new Random();
		StringBuilder sb = new StringBuilder(8);
		sb.append(r.nextInt(99999999));
		int len = sb.length();
		if (len < 8) {
			for (int i = 0; i < 8 - len; i++) {
				sb.append("0");
			}
		}
		String salt = sb.toString();
		return salt;
	}
}
