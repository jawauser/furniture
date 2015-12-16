package com.jawa.furniture.cookie;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jawa.furniture.common.Config;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Encrypt {
    private static Log log = LogFactory.getLog(Encrypt.class);
    public final static String md5(String src, String key) {
    	char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        				'a', 'b', 'c', 'd', 'e', 'f'};
    	try {
	    	byte[] strTemp = (key + src).getBytes();
	        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	        mdTemp.update(strTemp);
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char str[] = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	        	byte byte0 = md[i];
	        	str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	        	str[k++] = hexDigits[byte0 & 0xf];
	      	}
      		return new String(str);
    	} catch (Exception e) {
      		return null;
    	}
  	}
    public final static String md5(String s) {
    	char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        				'a', 'b', 'c', 'd', 'e', 'f'};
    	try {
	    	byte[] strTemp = s.getBytes();
	        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	        mdTemp.update(strTemp);
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char str[] = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	        	byte byte0 = md[i];
	        	str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	        	str[k++] = hexDigits[byte0 & 0xf];
	      	}
      		return new String(str);
    	} catch (Exception e) {
      		return null;
    	}
  	}
    public static String base64Encode(String src){
    	//Base64 b64 = new Base64();
    	BASE64Encoder b64 = new BASE64Encoder();
    	//log.debug("encode src:"+src);
    	try{
    		String encode = new String(b64.encode(src.getBytes("GBK")));
    		encode = encode.replaceAll(" ", "").replaceAll("\r", "").replaceAll("\n", "");
    		//log.debug("encode :"+encode);
    		return encode;
    	}catch(UnsupportedEncodingException e){
    		return null;
    	}
    }
    
    public static String encode(String src){
    	String key = getKey(8, 18);
    	//Base64 b64 = new Base64();
    	BASE64Encoder b64 = new BASE64Encoder();
    	String encodeStr = "";
		try {
			encodeStr = new String(b64.encode(replacement(src, key).getBytes("GBK")));
		} catch (UnsupportedEncodingException e) {
		}
    	//log.error("encode:"+encodeStr);
    	return encodeStr;
    }
    public static String base64Decode(String src){
    	//Base64 b64 = new Base64();
    	BASE64Decoder b64 = new BASE64Decoder();
    	//log.debug("decode src:"+src);
    	try{
    		String decode = new String(b64.decodeBuffer(src));
    		//log.debug("decode :"+decode);
    		return decode;
    	}catch(Exception e){
    		return null;
    	}
    }
    public static String decode(String src){
    	String key = getKey(8, 18);
    	//Base64 b64 = new Base64();
    	BASE64Decoder b64 = new BASE64Decoder();
    	String decodeStr = "";
		try {
			decodeStr = replacement(new String(b64.decodeBuffer(src)),key);
		} catch (Exception e) {
		}
    	//log.error("decode:"+decodeStr);
    	return decodeStr;
    }
    
    protected static String getKey(int start, int lenght){
		String md5Str = md5(Config.getString("encode.key"));
    	return md5Str.substring(start, start+lenght);
    }
    /* 
   //由于java和php对字符长度的定义不同，会导致在java中加密过的数据在php中不能解密
    private String replacement(String src, String key){
    	StringBuffer resultSB = new StringBuffer(128);
    	int keyLen = key.length();
		int len = src.length();
		for(int i=0; i<len; i++){
			int k = i % keyLen;
			char xx = (char)( src.charAt(i) ^ key.charAt(k) );
			resultSB.append( xx );
		}
		System.out.println(resultSB.toString());
    	return resultSB.toString();
    }*/
   private static String replacement(String src, String key) {
		String result = null;
		try {
			byte[] keyBytes = key.getBytes("GBK");
			int keyLen = keyBytes.length;
			byte[] srcBytes = src.getBytes("GBK");
			int len = srcBytes.length;
			byte[] bb = new byte[len];
			for (int i = 0; i < len; i++) {
				int k = i % keyLen;
				byte b = (byte) (srcBytes[i] ^ keyBytes[k]);
				bb[i] = b;
			}
			result = new String(bb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    public final static String MD5(String s,String encode) {
    	char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        				'a', 'b', 'c', 'd', 'e', 'f'};
    	try {
	    	byte[] strTemp = s.getBytes(encode);
	        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	        mdTemp.update(strTemp);
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char str[] = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	        	byte byte0 = md[i];
	        	str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	        	str[k++] = hexDigits[byte0 & 0xf];
	      	}
      		return new String(str);
    	} catch (Exception e) {
      		return null;
    	}
  	}
}
