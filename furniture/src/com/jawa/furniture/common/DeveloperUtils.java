package com.jawa.furniture.common;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class DeveloperUtils {
	/**
	 * 编码-加密
	 * @param str
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String str, String charset)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(str))
			return "";
		return URLEncoder.encode(str, charset);
	}
	/**
	 * 解码-解密
	 * @param str
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String str, String charset)
			throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(str))
			return "";
		return URLDecoder.decode(str, charset);
	}

	/**
	 * 获取随机字符串(大小写字母、数字)
	 * 
	 * @param length
	 *            字符串长度
	 * @return 字符串
	 */
	public static String getRandomString(int length) {
		String buffer = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}

		return sb.toString();
	}

	/**
	 * 用户密码强度判断-安全中心使用
	 * 
	 * @param passwordStr
	 * @return
	 */
	public static int checkPasswordSafe(String passwordStr, String username,
			String email) {
		String str = "^[0-9]{1,20}$"; // 不超过20位的数字组合
		String str1 = "^[0-9|a-z|A-Z|\\S\\s]{1,20}$"; // 由字母、数字组成，不超过20位 特殊字符
		String str2 = "^[a-zA-Z]{1,20}$"; // 由字母不超过20位
		if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(email)) {
			if (username.equals(passwordStr)
					|| email.substring(0, email.indexOf("@")).equals(
							passwordStr)) {// 密码和用户名一样:弱 - 密码和邮箱@前面的一样：弱
				return 0;
			}
		}
		if (passwordStr.trim().length() < 6) {// 去掉空格，
			return 0;
		}
		if (passwordStr.matches(str)) { // 纯数子：中
			if (passwordStr.length() == 6) {
				return 0; // 123456这样的数字返回0：弱
			}
			int p0 = Integer.valueOf(passwordStr.charAt(0));
			int samCount = 0;
			for (int i = 0; i < passwordStr.length(); i++) {
				if (p0 == Integer.valueOf(passwordStr.charAt(i))) {
					samCount++;
				}
			}
			if (samCount == passwordStr.length()) {
				return 0; // 111111这样的数字返回0：弱
			}
			return 1;
		}

		if (passwordStr.matches(str2)) { // 纯字母 返回1：中
			if (passwordStr.length() == 6) {
				return 0; // abcdef这样的字母返回0：弱
			}
			String p0 = String.valueOf(passwordStr.charAt(0));
			int samCount = 0;
			for (int i = 0; i < passwordStr.length(); i++) {
				if (p0.equals(String.valueOf(passwordStr.charAt(i)))) {
					samCount++;
				}
			}
			if (samCount == passwordStr.length()) {
				return 0; // aaaaaaaaaaaaaaa这样的数字返回0：弱
			}
			return 1;
		}

		if (passwordStr.matches(str1) && passwordStr.length() >= 6) {// 中英文组合,长度>=6
			return 2;
		}
		return 1;// 默认密码是中
	}
}
