package com.jawa.furniture.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jawa.furniture.common.Config;


public class DoCookie {
	/**
	 * 添加或修改cookie
	 * 
	 * @param name
	 * @param value
	 * @param path
	 * @param expireDate
	 * @param response
	 */
	public static void saveOrUpdateCookie(String name, String value, String path, int expireDate,
			HttpServletResponse response) {
		Cookie ck = new Cookie(name, value);
		ck.setMaxAge(expireDate);
		if (null != path && !"".equals(path)) {
			ck.setPath(path);
		}
		response.addCookie(ck);
	}

	/**
	 * 删除指定的cookie
	 */
	public static void deleteCookie(String name, String path, HttpServletResponse response) {
		Cookie killMyCookie = new Cookie(name, null);
		killMyCookie.setMaxAge(-200);
		if (null != path && !"".equals(path)) {
			killMyCookie.setPath(path);
		}
		response.addCookie(killMyCookie);
	}

	/**
	 * 通过一个cookie's name 取得该cookie's value
	 */
	public static String getCookieValueByName(HttpServletRequest request, String cookieName) {
		String cookieValue = "";
		Cookie ck[] = request.getCookies();
		for (int i = 0; ck != null && i < ck.length; i++) {
			if (ck[i].getName() == null)
				continue;
			if (ck[i].getName().equals(cookieName)) {
				cookieValue = ck[i].getValue();
				break;
			}
		}
		return cookieValue;
	}

	/**
	 * 通过一个cookie's name 取得该cookie对象
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
		Cookie cookie = null;
		Cookie ck[] = request.getCookies();
		for (int i = 0; ck != null && i < ck.length; i++) {
			if (ck[i].getName() == null)
				continue;
			if (ck[i].getName().equals(cookieName)) {
				cookie = ck[i];
				break;
			}
		}
		return cookie;
	}

	/**
	 * 查找指定的cookie 是否存在
	 */
	public static boolean isExitCookieByName(HttpServletRequest request, String cookieName) {
		Cookie ck[] = request.getCookies();
		for (int i = 0; ck != null && i < ck.length; i++) {
			if (ck[i].getName() == null)
				continue;
			if (ck[i].getName().equals(cookieName)) {// 存在
				return true;
			}
		}
		return false;
	}

	/**
	 * 用户登录处理cookie
	 */

	public static void login(HttpServletResponse response, String userName, String password, String userId,
			String email, String beforeLoginTime, String memorize) {
		String value = CookieUtil.cookieValue(userName, password, userId, beforeLoginTime);
		Cookie ck = new Cookie(Config.getString("jawa.cookie.name"), value);
		ck.setPath("/");
		int maxAge = 60 * 60 * 24;
		if (StringUtils.isNotBlank(memorize)) {
			ck.setMaxAge(maxAge * 30);
		} else {
			ck.setMaxAge(maxAge);
		}

		ck.setDomain(".jawa.com");
		response.addCookie(ck);
		// quickLogin(response,userName,userId);
	}

	public static void quickLogin(HttpServletResponse response, String userName, String userId) {
		String value = CookieUtil.cookieValue(userName, "", userId, "");
		Cookie ck = new Cookie(Config.getString("quickUser"), value);
		ck.setPath("/");
		ck.setDomain(".jawa.com");
		response.addCookie(ck);
	}

	public static void logoutEgou(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(Config.getString("jawa.cookie.name"))) {
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0);
					cookies[i].setDomain(".jawa.com");
					response.addCookie(cookies[i]);
				}
			}
		}
	}

}
