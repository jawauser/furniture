package com.jawa.furniture.common;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions {
	/**
	 * 从jdbc查询结果集里获得long
	 * 
	 * @param o
	 * @return
	 */
	public static long asLong(Object o) {
		return o == null ? 0 : ((Number) o).longValue();
	}

	/**
	 * 从jdbc查询结果集里获得long
	 * 
	 * @param o
	 * @return
	 */
	public static int asInt(Object o) {
		return o == null ? 0 : ((Number) o).intValue();
	}

	/**
	 * 从jdbc查询结果集里获得string
	 */
	public static String asStr(Object o) {
		return o == null ? null : o.toString();
	}

	/**
	 * 从jdbc查询结果集里根据字符串转换日期类型，只适用于结果集里是标准 日期字符串
	 * 
	 * @param o
	 * @return
	 */
	public static Date asDate(Object o) {
		if (o == null)
			return null;
		else {
			if (o instanceof Date)
				return (Date) o;
			String time = o.toString();
			SimpleDateFormat sdf = null;
			if (time.length() > 10) {
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			} else {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			try {
				return sdf.parse(time);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void rollback(Connection con) {
		if (con == null)
			return;
		try {
			con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
