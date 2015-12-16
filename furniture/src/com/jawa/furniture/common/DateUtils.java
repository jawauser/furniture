package com.jawa.furniture.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtils {
	/**
	 * 计算周几
	 * @param time
	 * @return
	 */
	public static Map<String,Object> dateFormat(Long time){
		Map<String,Object> result=new HashMap<String,Object>();
		SimpleDateFormat week = new SimpleDateFormat("E", Locale.CHINA);
		Date date=new Date(time);
		result.put("date", date);
		result.put("weekCn", week.format(date).substring(2));
		return result;
	}
}
