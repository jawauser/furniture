package com.jawa.furniture.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;

import com.jawa.furniture.common.Config;

public class JawaCookie {
	// private static Log log = LogFactory.getLog(getClass());
	public static boolean validate(String userInfoStr) {
		if (null == userInfoStr || "null".equals(userInfoStr.trim())
				|| "".equals(userInfoStr.trim())) {
			return false;
		}
		// String [] userInfo = getUserInfo(cookieValue, request);
		String[] userInfo = userInfoStr.split("//");
		if (5 == userInfo.length) {
			String md5Key = Encrypt.md5(userInfo[1] + userInfo[0]);
			byte[] userNameBytes = null;
			try {
				userNameBytes = userInfo[0].getBytes("GBK");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			int halfUL = userNameBytes.length / 2;
			if (md5Key.substring(0, halfUL).equals(userInfo[4])) {
				return true;
			}
		}
		return false;
	}

	public static String getUserName(HttpServletRequest request) {
		String value = getValue(request);
		String[] userInfo = getUserInfo(value);
		if (null != userInfo) {
			return userInfo[0];
		}
		return null;
	}

	public static String getQuickUserName(HttpServletRequest request) {
		String value = getQuickValue(request);
		String[] userInfo = getUserInfo(value);
		if (null != userInfo) {
			return userInfo[0];
		}
		return null;
	}

	private static String[] getUserInfo(String cookieValue) {
		// String decode = e.decode(cookieValue, request);
		String decode = null;
		// 先用简单的方式解密
		decode = Encrypt.base64Decode(URLDecoder.decode(cookieValue));
		if (null != decode && validate(decode)) {
			return decode.split("//");
		} else {
			decode = Encrypt.decode(cookieValue);
			if (null != decode && validate(decode)) {
				return decode.split("//");
			} else {
				return null;
			}
		}
	}

	public static String getPassword(HttpServletRequest request) {
		String value = getValue(request);
		String[] userInfo = getUserInfo(value);
		if (null != userInfo) {
			return userInfo[1];
		}
		return null;
	}

	public static String getUserId(HttpServletRequest request) {
		String value = getValue(request);
		String[] userInfo = getUserInfo(value);
		if (null != userInfo) {
			return userInfo[2];
		}
		return null;
	}

	/**
	 * 已经无效
	 */
	@Deprecated
	public static String getEmail(HttpServletRequest request) {
		/*
		 * String value = getValue(request); String [] userInfo =
		 * getUserInfo(value); if(null!=userInfo){ return userInfo[3]; }
		 */
		return null;
	}

	public static String getBeforeLoginTime(HttpServletRequest request) {
		String value = getValue(request);
		String[] userInfo = getUserInfo(value);
		if (null != userInfo) {
			return userInfo[3];
		}
		return null;
	}

	private static String getValue(HttpServletRequest request) {
		return DoCookie.getCookieValueByName(request,
				Config.getString("jawa.cookie.name"));
	}
	

	private static String getQuickValue(HttpServletRequest request) {
		return DoCookie.getCookieValueByName(request,
				Config.getString("quickUser"));
	}

	public static String getOrderUserId(HttpServletRequest request) {
		String uid = getUserId(request);
		if (uid == null) {
			String value = getQuickValue(request);
			if (null != value) {
				String[] userInfo = getUserInfo(value);
				if (null != userInfo) {
					return userInfo[2];
				}
			}
		} else {
			return uid;
		}
		return null;
	}

	public static RegUser getUser(HttpServletRequest request) {
		String value = getValue(request);
		String[] userInfo = getUserInfo(value);
		if (null != userInfo) {
			RegUser user = new RegUser();
			user.setId(NumberUtils.toLong(userInfo[2]));
			user.setUsername(userInfo[0]);
			user.setEmail(userInfo[3]);
			user.setPassword(userInfo[1]);
			return user;
		}
		return null;
	}
}
