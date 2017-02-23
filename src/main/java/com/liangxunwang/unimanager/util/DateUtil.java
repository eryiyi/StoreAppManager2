package com.liangxunwang.unimanager.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhl on 2015/2/1.
 */
public class DateUtil {

    /**
     * 根据日期获得毫秒值
     * @param str
     * @return
     */
    public static long getMs(String str, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 根据毫秒值获得日期
     * @return
     */
    public static String getDate(String time, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(Long.parseLong(time));
        return sdf.format(date);
    }

    /**
     * 获得当天的毫秒值开始
     * @return
     */
    public static long getStartDay(){
        DateTime time = new DateTime();
//        String date = time.toString("yyyy-MM-dd HH:mm");
        String date = getDateFormat1() +" 00:00";
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        time = DateTime.parse(date, format);
        long start = time.getMillis();
        return start;
    }

    public static long getEndDay(){
        DateTime time = new DateTime();
//        String date = time.toString("yyyy-MM-dd HH:mm");
        String date = getDateFormat1() +" 23:59";
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        time = DateTime.parse(date, format);
        long start = time.getMillis();
        return start+Constants.DAY_MILLISECOND;
    }

    /**
     *
     * @param now  当前时间
     * @param day  比当前时间差几天  正的就是当天时间之前几天   负的就是当前时间之后几天
     * @return
     */
    public static Object[] getDayInterval(long now, int day){
        long mis = now-(day*Constants.DAY_MILLISECOND);
        DateTime time = new DateTime(mis);
        String date = time.toString("yyyy-MM-dd HH:mm");
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        time = DateTime.parse(date, format);
        long start = time.getMillis();
        return new Object[]{start, start+Constants.DAY_MILLISECOND, date};
    }

    /**
     * 返回当前日期+时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return getFormatDateTime(new Date(), "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    /**
     *  查询当前日期前(后)x天的日期
     *
     * @param millis 当前日期毫秒数
     * @param day 天数（如果day数为负数,说明是此日期前的天数）
     * @return yyyy-MM-dd
     */
    public static String beforLongDate(long millis, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        c.add(Calendar.DAY_OF_YEAR, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(c.getTimeInMillis());
        return sdf.format(date);
    }

    public static String  getDateAndTimeThree(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//可以方便地修改日期格式
        String hehe = dateFormat.format( now );
        return hehe;
    }
    public static String  getDateFormat1(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
        String hehe = dateFormat.format( now );
        return hehe;
    }
}
