package com.hr.hr_common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        int firstDay = cal.getMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        return sdf.format(cal.getTime());
    }

    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        return sdf.format(cal.getTime());
    }

    public static String getStrMonth(int month) {
        String[] strs = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        if (month > 0 && month < 13) {
            return strs[month - 1];
        } else {
            return "一";
        }
    }

    public static String parseTimestampToStr(Timestamp timestamp, String timeFormat) {
        SimpleDateFormat df = new SimpleDateFormat(timeFormat);
        return df.format(timestamp);
    }

    public static String timestampToDate(long time, String timeFormat) {
        if (time < 10000000000L) {
            time = time * 1000;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        return sdf.format(new Date(Long.parseLong(String.valueOf(time))));
    }





}
