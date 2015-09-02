package com.asiainfo.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTest {
	public static void main(String[] args) {

		SimpleDateFormat sf=new SimpleDateFormat("y年M月d日 E H时m分s秒",Locale.CHINA);
		SimpleDateFormat sf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		Calendar cal=Calendar.getInstance(Locale.CHINA);
		cal.setFirstDayOfWeek(Calendar.MONDAY);//针对中国已周一为一周的第一天
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		System.out.println("周一时间:"+sf1.format(cal.getTime()));
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		System.out.println("周日时间:"+sf1.format(cal.getTime()));
	}
}
