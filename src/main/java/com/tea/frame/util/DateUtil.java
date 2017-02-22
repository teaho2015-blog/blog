
package com.tea.frame.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class DateUtil {
    public final static String yyyy = "yyyy";
    public final static String mm = "M";
    public final static String MM = "MM";
    public final static String dd = "d";
    public final static String yyyyMM = "yyyyMM";
    public final static String yyyy_MM = "yyyy_MM";
    public final static String yyyy_MM_dd = "yyyy-MM-dd";
    public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final static String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss SSS";
    public final static String HH_mm_ss = "HH:mm:ss";

    public static String now(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(Calendar.getInstance().getTime());
    }

    public static String now_yyyy() {
        return now(yyyy);
    }

    public static String now_yyyyMM() {
        return now(yyyyMM);
    }

    public static String now_yyyy_MM() {
        return now(yyyy_MM);
    }

    public static String now_yyyy_MM_dd() {
        return now(yyyy_MM_dd);
    }

    public static String now_yyyy_MM_dd_HH_mm_ss() {
        return now(yyyy_MM_dd_HH_mm_ss);
    }

    public static String now_yyyy_MM_dd_HH_mm_ss_SSS() {
        return now(yyyy_MM_dd_HH_mm_ss_SSS);
    }

    public static String now_mm() {
        return now(mm);
    }

    public static String now_MM() {
        return now(MM);
    }

    public static String now_dd() {
        return now(dd);
    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy);
        String year = sdf.format(date);
        return Integer.valueOf(year);
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(mm);
        String month = sdf.format(date);
        return Integer.valueOf(month);
    }

    /**
     * 获取下一月的时间(格式：201411)
     *
     * @param dateStr
     *            201411
     * @return
     */
    public static String getNextMonth(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMM);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dateStr));
            cal.add(Calendar.MONTH, 1);// 获得下一个月日期
            String nextMonth = sdf.format(cal.getTime());
            return nextMonth;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

    /**
     * 获取下一年的时间(格式：2014)
     *
     * @param dateStr
     *            2014
     * @return
     */
    public static String getNextYear(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dateStr));
            cal.add(Calendar.YEAR, 1);// 获得下一个月日期
            String nextYear = sdf.format(cal.getTime());
            return nextYear;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }


    /**
     * 获取上一年的时间(格式：2014)
     *
     * @param dateStr
     *            2014
     * @return
     */
    public static String getBeforeYear(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dateStr));
            cal.add(Calendar.YEAR, -1);// 获得下一个月日期
            String nextYear = sdf.format(cal.getTime());
            return nextYear;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

    /**
     * 获取本月的时间(格式：201411)
     *
     * add by fuweijian
     * @return
     */
    public static String getNowYearAndMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMM);
        Calendar cal = Calendar.getInstance();
        String month = sdf.format(cal.getTime());
        return month;
    }

    /**
     * 将日期时间型转成字符串 如:" 2002-07-01 11:40:02"
     *
     * @param date
     *            日期时间 " 2002-07-01 11:40:02"
     * @return String 转换后日期时间字符串
     */
    public static String dateToStr_yyyy_MM_dd_HH_mm_ss(Date date) {
        return dateToStr(date, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将日期型转成字符串 如:"2002-07-01"
     *
     * @param date
     *            日期 "2002-07-01"
     * @return String 转换后日期字符串
     */
    public static String dateToStr_yyyy_MM_dd(Date date) {
        return dateToStr(date, yyyy_MM_dd);
    }

    /**
     * 将字符串型(英文格式)转成日期型 如: "Tue Dec 26 14:45:20 CST 2000"
     *
     * @param shorDateStr
     *            字符串 "Tue Dec 26 14:45:20 CST 2000"
     * @return Date 日期
     */
    public static Date strToDateEN(String shorDateStr) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "EEE MMM dd hh:mm:ss 'CST' yyyy", java.util.Locale.US);
            return sdf.parse(shorDateStr);
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * 将字符串型(中文格式)转成日期型 如:"2002-07-01 22:09:55"
     *
     * @param datestr
     *            字符串 "2002-07-01 22:09:55"
     * @return Date 日期
     */
    public static Date strToDateCN_yyyy_MM_dd_HH_mm_ss(String datestr) {
        Date date = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = fmt.parse(datestr);
        } catch (Exception e) {
            return date;
        }
        return date;
    }

    /**
     *
     * @param datestr
     * @return
     */
    public static Date strToDateCN_yyyy_MM_dd(String datestr) {
        Date date = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            date = fmt.parse(datestr);
        } catch (Exception e) {
            return date;
        }
        return date;
    }

    /**
     * 将日期时间型转成时分秒字符串 如:" 11:40:02"
     *
     * @param date
     * @return
     */
    public static String dateToStrCN_HH24_MM_SS(Date date) {
        String hh_mm_ss = "";
        try {
            DateFormat fmt = DateFormat.getTimeInstance();
            hh_mm_ss = fmt.format(date);
        } catch (Exception e) {
            return hh_mm_ss;
        }
        return hh_mm_ss;
    }

    /**
     * 转换util.date-->sql.date
     *
     * @param inDate
     * @return
     */
    public static java.sql.Date UtilDateToSqlDate(Date inDate) {
        return new java.sql.Date(getDateTime(inDate));
    }

    private static long getDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        cal.set(year, month, day, 0, 0, 0);
        long result = cal.getTimeInMillis();
        result = result / 1000 * 1000;
        return result;
    }

    /**
     * 遍历刚从数据库里查出来的Map，将里面Timestamp格式化成指定的pattern
     *
     * @param target
     *            目标map,就是一般是刚从数据库里查出来的
     * @param pattern
     *            格式化规则，从自身取
     */
    @Deprecated
    public static void formatMapDate(Map target, String pattern) {
        for (Object item : target.entrySet()) {
            Map.Entry entry = (Map.Entry) item;
            if (entry.getValue() instanceof Timestamp) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                entry.setValue(sdf.format((Timestamp) entry.getValue()));
            }
        }
    }

    /**
     * 日期转化为大小写 chenjiandong 20090609 add
     *
     * @param date
     * @param type
     *            1;2两种样式1为简体中文，2为繁体中文
     * @return
     */
    public static String dataToUpper(Date date, int type) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return numToUpper(year, type) + "年" + monthToUppder(month, type) + "月"
                + dayToUppder(day, type) + "日";
    }

    /**
     * 将数字转化为大写
     *
     * @param num
     * @param type
     * @return
     */
    public static String numToUpper(int num, int type) {// type为样式1;2
        String u1[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String u2[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        if (type == 1) {
            for (int i = 0; i < str.length; i++) {
                rstr = rstr + u1[Integer.parseInt(str[i] + "")];
            }
        } else if (type == 2) {
            for (int i = 0; i < str.length; i++) {
                rstr = rstr + u2[Integer.parseInt(str[i] + "")];
            }
        }
        return rstr;
    }

    /**
     * 月转化为大写
     *
     * @param month
     * @param type
     * @return
     */
    public static String monthToUppder(int month, int type) {
        if (month < 10) {
            return numToUpper(month, type);
        } else if (month == 10) {
            return "十";
        } else {
            return "十" + numToUpper((month - 10), type);
        }
    }

    /**
     * 日转化为大写
     *
     * @param day
     * @param type
     * @return
     */
    public static String dayToUppder(int day, int type) {
        if (day < 20) {
            return monthToUppder(day, type);
        } else {
            char[] str = String.valueOf(day).toCharArray();
            if (str[1] == '0') {
                return numToUpper(Integer.parseInt(str[0] + ""), type) + "十";
            } else {
                return numToUpper(Integer.parseInt(str[0] + ""), type) + "十"
                        + numToUpper(Integer.parseInt(str[1] + ""), type);
            }
        }
    }

    // 取得时间格式为 2013-04-24 00:00:00.0
    public static Timestamp getTimestamp() {
        Timestamp nowDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(new Date());
        try {
            nowDate = new Timestamp(format.parse(dateString).getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nowDate;
    }

    // 取得时间格式为 2013-04-24 00:00:00.0
    public static Timestamp getTimestamp(Date date) {
        Timestamp nowDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(date);
        try {
            nowDate = new Timestamp(format.parse(dateString).getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nowDate;
    }

    // 取得时间格式为 2013-04-24 00:00:00.0
    public static Timestamp getTimestamp(String datestr) {
        Timestamp nowDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            nowDate = new Timestamp(format.parse(datestr).getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nowDate;
    }

    // 返回时年月日时分秒格式的时间
    public static Timestamp getTimestamp_yyyy_MM_dd_HH_mm_ss() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(new Date());
        Timestamp nowDate = null;
        try {
            nowDate = new Timestamp(format.parse(dateString).getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nowDate;
    }

    // 返回时年月日时分秒格式的时间
    public static Timestamp getTimestamp_yyyy_MM_dd_HH_mm_ss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(date);
        Timestamp nowDate = null;
        try {
            nowDate = new Timestamp(format.parse(dateString).getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nowDate;
    }


    /**
     * 得到前几天的现在
     * 利用GregorianCalendar类的set方法来实现
     * @param num
     * @return Date
     */
    public static Date getPreDayTime(int num){
        GregorianCalendar gregorianCal = new GregorianCalendar();
        gregorianCal.set(Calendar.DATE, gregorianCal.get(Calendar.DATE) - num);
        return gregorianCal.getTime();
    }
    /**
     * 得到后几天的现在时间
     * 利用GregorianCalendar类的set方法来实现
     * @param num
     * @return Date
     */
    public static Date getNextDayTime(int num){
        GregorianCalendar gregorianCal = new GregorianCalendar();
        gregorianCal.set(Calendar.DATE, gregorianCal.get(Calendar.DATE) + num);
        return gregorianCal.getTime();
    }
    /**
     * 得到前几个月的现在
     * 利用GregorianCalendar类的set方法来实现
     * @param num
     * @return Date
     */
    public static Date getPreMonthTime(int num){
        GregorianCalendar gregorianCal = new GregorianCalendar();
        gregorianCal.set(Calendar.MONTH, gregorianCal.get(Calendar.MONTH) - num);
        return gregorianCal.getTime();
    }
    /**
     * 得到后几个月的现在时间
     * 利用GregorianCalendar类的set方法来实现
     * @param num
     * @return Date
     */
    public static Date getNextMonthTime(int num){
        GregorianCalendar gregorianCal = new GregorianCalendar();
        gregorianCal.set(Calendar.MONTH, gregorianCal.get(Calendar.MONTH) + num);
        return gregorianCal.getTime();
    }
    /**
     * 获取当前时间的Timestamp格式的时间 包括时分秒
     */
    public static Timestamp getCurrentTimestamp() {
        Timestamp nowDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(new Date());
        try {
            nowDate = new Timestamp(format.parse(dateString).getTime());
        } catch (ParseException e) {
            ExceptionUtil.throwRuntimeException(e);
        }
        return nowDate;
    }
}
