package com.jawa.furniture.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.jawa.furniture.common.Config;

public class CookieUtil {
	public static String cookieValue(String userName, String password, String userId,String beforeLoginTime ){
		Encrypt e = new Encrypt();
//		String md5Key = Encrypt.md5(password+userName);
//		int halfUL = userName.length()/2;
//		String cookieValue = userName+"//"+password+"//"+userId+"//"+email+"//"+md5Key.substring(0, halfUL);
//		String encode = Encrypt.encode(cookieValue, request);
		String md5Key = Encrypt.md5(password+userName);
		byte [] userNameBytes= null;
		try {
			userNameBytes = userName.getBytes("GBK");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		int halfUL = userNameBytes.length/2;
		String cookieValue = userName+"//"+password+"//"+userId+"//"+beforeLoginTime+"//"+md5Key.substring(0, halfUL);
		//String encode = Encrypt.encode(cookieValue);
		String encode = URLEncoder.encode(Encrypt.base64Encode(cookieValue));
		return encode;			
	}
	public static void deleteCookie(HttpServletResponse response){
		Cookie ck=new Cookie(Config.getString("jawa.cookie.name"), "");
		ck.setPath("/");
		ck.setMaxAge(-1);
		ck.setDomain(".jawa.com");
		response.addCookie(ck);
	}
}
