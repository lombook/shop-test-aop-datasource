package com.jinglitong.shop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date stringToDate1(String str) throws ParseException {
	    DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date=format.parse(str);
	    System.out.print(date);
	    return date;
	}
}
