package com.hr.hr_common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date parseString2Date(String dateString) throws ParseException {
        if (dateString == null) {
            return null;
        }
        return parseString2Date(dateString, "yyyy-MM-dd");
    }

    public static Date parseString2Date(String dateString, String pattern) throws ParseException {
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }

    public static String parseDate2String(Date date) {
        if (date == null) {
            return null;
        }
        return parseDate2String(date, "yyyy-MM-dd");
    }

    public static String parseDate2String(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }



}
