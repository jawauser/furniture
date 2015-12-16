package com.jawa.furniture.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class UserUtils {

	/**
	 * 通过一个cookie's name 取得该cookie's value
	 */
	public static String getCookieValueByName(HttpServletRequest request,
			String cookieName) {
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
	 * 获取用户IP
	 * @date 2015年11月27日
	 * @param
	 */
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        
        //经过代理服务器添加的ip
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
        	// 多次反向代理后会有多个IP值，第一个为真实IP。
        	int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
        return ip;
    }
}
