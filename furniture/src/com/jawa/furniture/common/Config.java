package com.jawa.furniture.common;

import java.util.ResourceBundle;

public class Config {
	
	private static ResourceBundle bundle;

	static {
		try {
			bundle = ResourceBundle.getBundle("config");

		} catch (Exception e) {

		}
	}

	public static String getString(String key) {
		try {
			String result = bundle.getString(key);
			return result;
		} catch (java.util.MissingResourceException mre) {

			return "";
		}

	}

	public static String getString(String bundle, String key) {
		try {
			ResourceBundle b = ResourceBundle.getBundle(bundle);
			String value = b.getString(key);
			return value;
		} catch (java.util.MissingResourceException mre) {
			return "";
		} catch (Exception e) {
			return "";
		}
	}
}
