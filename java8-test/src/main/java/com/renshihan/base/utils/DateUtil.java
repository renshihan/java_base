package com.renshihan.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
public class DateUtil extends DateUtils {

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> localMap = new HashMap<>();

    public static String FORMAT_DAY = "yyyy-MM-dd";
    public static String FORMAT_HOUR = "yyyy-MM-dd HH:00:00";
    public static String FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static String FORMATE_YMD_T_HMS = "yyyy-MM-dd'T'HH:mm:ss";

    private static ZoneId zoneId = ZoneId.systemDefault();


    //获取小时
    private static SimpleDateFormat getHourFormat() {

        return getSimpleDateFormat(FORMAT_HOUR);
    }

    //获取天
    private static SimpleDateFormat getDayFormat() {

        return getSimpleDateFormat(FORMAT_DAY);
    }

    //获取一个SimpleDateFormat对象
    private static SimpleDateFormat getSimpleDateFormat(String pattern) {

        ThreadLocal<SimpleDateFormat> local = localMap.get(pattern);
        if (local == null) {
            local = new ThreadLocal<SimpleDateFormat>() {

                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(pattern);
                }
            };
            localMap.put(pattern, local);
        }
        return local.get();
    }

    //获取当天的时间
    public static Calendar getToday() {

        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

    //获取当天的时间前一个小时
    public static Calendar getTodayBefore1Hour() {

        Calendar calendar = getToday();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        return calendar;
    }

    //获取当天的时间前两个小时
    public static Calendar getTodayBefore2Hour() {
        Calendar calendar = getToday();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 2);
        return calendar;
    }

    //获取当天的时间前四个小时
    public static Calendar getTodayBefore4Hour() {
        Calendar calendar = getToday();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 4);
        return calendar;
    }

    //时间前移四个小时
    public static Calendar getBefore4Hour(String timeStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_HOUR);
            Date begin = format.parse(timeStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(begin);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 4);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //获取当前的时间-小时
    public static String hourFormat(Calendar calendar) {

        return getHourFormat().format(calendar.getTime());
    }

    //获取后一个小时
    public static String getAfter1Hour(String time) throws ParseException {

        SimpleDateFormat hourFormat = getHourFormat();
        Calendar cdr = getToday();
        cdr.setTime(hourFormat.parse(time));
        int day = cdr.get(Calendar.HOUR_OF_DAY);
        cdr.set(Calendar.HOUR_OF_DAY, day + 1);
        return hourFormat.format(cdr.getTime());
    }

    public static Date getAfterNHour0M0S0MS(Date time, int n) {
        Calendar cdr = getToday();
        cdr.setTime(time);
        int day = cdr.get(Calendar.HOUR_OF_DAY);
        cdr.set(Calendar.HOUR_OF_DAY, day + n);
        cdr.set(Calendar.MINUTE, 0);
        cdr.set(Calendar.SECOND, 0);
        cdr.set(Calendar.MILLISECOND, 0);
        return cdr.getTime();
    }

    //当前时间提前8小时
    public static String getTodayBefore8Hour() {
        SimpleDateFormat dayFormat = getDayFormat();
        Calendar cdr = getToday();
        int day = cdr.get(Calendar.HOUR_OF_DAY);
        cdr.set(Calendar.HOUR_OF_DAY, day - 8);
        return dayFormat.format(cdr.getTime());
    }


    //获取时间字符串
    public static String format(Calendar calendar) {

        return getDayFormat().format(calendar.getTime());
    }

    public static String format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }

    public static String format(Integer seconds, String format) {
        if (seconds == null) {
            return "-";
        } else {
            return format(new Date(new Long(seconds) * 1000), format);
        }
    }

    //获取前一天的时间
    public static String getBeforeDay(Calendar cdr) {

        int day = cdr.get(Calendar.DATE);
        cdr.set(Calendar.DATE, day - 1);
        //是不是同一年
        return getDayFormat().format(cdr.getTime());
    }

    //获取前36小时的时间
    public static String getBefore36Hour(Calendar cdr) {
        int hour = cdr.get(Calendar.HOUR_OF_DAY);
        cdr.set(Calendar.HOUR_OF_DAY, hour - 36);
        //是不是同一年
        return getDayFormat().format(cdr.getTime());
    }

    public static String getBeforeDay(String time) throws ParseException {

        SimpleDateFormat dayFormat = getDayFormat();
        Calendar cdr = Calendar.getInstance();
        cdr.setTime(dayFormat.parse(time));
        int day = cdr.get(Calendar.DATE);
        cdr.set(Calendar.DATE, day - 1);
        return dayFormat.format(cdr.getTime());
    }

    public static String getBeforeDay(Date time) {

        SimpleDateFormat dayFormat = getDayFormat();
        Calendar cdr = Calendar.getInstance();
        cdr.setTime(time);
        int day = cdr.get(Calendar.DATE);
        cdr.set(Calendar.DATE, day - 1);
        return dayFormat.format(cdr.getTime());
    }

    public static Date getBeforeDay0H0M0S0MS(Date time) {
        Calendar cdr = Calendar.getInstance();
        cdr.setTime(time);
        int day = cdr.get(Calendar.DATE);
        cdr.set(Calendar.DATE, day - 1);
        cdr.set(Calendar.HOUR_OF_DAY, 0);
        cdr.set(Calendar.MINUTE, 0);
        cdr.set(Calendar.SECOND, 0);
        cdr.set(Calendar.MILLISECOND, 0);
        return cdr.getTime();
    }

    /**
     * 获得某天位移n天的Date（年月日）
     * 例如:n=-1表示往后移一天，n=1表示往前移一天
     * System.out = "Thu Aug 02 00:00:00 CST 2018"
     */
    public static Date getBeforeNDays0H0M0S0MS(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDate yesterday = localDate.minusDays(n);
        ZonedDateTime zdt = yesterday.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date getBeforeNDays(Date date, int n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        LocalDate yesterday = localDate.minusDays(n);
        ZonedDateTime zdt = yesterday.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    //获取后一个小时
    public static String getAfterDay(String time) throws ParseException {

        SimpleDateFormat dayFormat = getDayFormat();
        Calendar cdr = Calendar.getInstance();
        cdr.setTime(dayFormat.parse(time));
        int day = cdr.get(Calendar.DATE);
        cdr.set(Calendar.DATE, day + 1);
        return dayFormat.format(cdr.getTime());
    }

    public static Calendar getBeforeDayCalendar(Calendar cdr) {

        int day = cdr.get(Calendar.DATE);
        cdr.set(Calendar.DATE, day - 1);
        return cdr;
    }

    //获取前天
    public static Calendar getBeforeYesterdayDayCalendar(Calendar cdr) {

        int day = cdr.get(Calendar.DATE);
        cdr.set(Calendar.DATE, day - 2);
        return cdr;
    }

    //获取24小时的时间
    public static String getBefore24Hour(Calendar cdr) {

        //获取前一天
        cdr.set(Calendar.HOUR_OF_DAY, cdr.get(Calendar.HOUR_OF_DAY) - 23);
        return getHourFormat().format(cdr.getTime());
    }

    //获取24小时的时间
    public static String getBefore24Hour(String date) {
        try {
            SimpleDateFormat hourFormat = getHourFormat();
            Calendar cdr = Calendar.getInstance();
            cdr.setTime(hourFormat.parse(date));
            int hour = cdr.get(Calendar.HOUR_OF_DAY);
            cdr.set(Calendar.HOUR_OF_DAY, hour - 23);
            return hourFormat.format(cdr.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return getBefore24Hour(getToday());
    }

    //获取5小时的时间
    public static String getBefore5Hour(Calendar cdr) {

        cdr.set(Calendar.HOUR_OF_DAY, cdr.get(Calendar.HOUR_OF_DAY) - 5);
        return getHourFormat().format(cdr.getTime());
    }

    //获取30天前的时间
    public static String getBefore30Day(Calendar cdr) {

        int day = cdr.get(Calendar.DAY_OF_YEAR);
        cdr.set(Calendar.DAY_OF_YEAR, day - 29);
        return getDayFormat().format(cdr.getTime());
    }

    //获取30天前的时间
    public static String getBefore30Day(String date) {

        try {
            SimpleDateFormat dayFormat = getDayFormat();
            Calendar cdr = Calendar.getInstance();
            cdr.setTime(dayFormat.parse(date));
            int day = cdr.get(Calendar.DAY_OF_YEAR);
            cdr.set(Calendar.DAY_OF_YEAR, day - 29);
            return dayFormat.format(cdr.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //默认时间
        return getBefore30Day(DateUtil.getToday());
    }


    // 获取当前时间秒
    public static Integer getNowSeconds() {

        return ((Long) (System.currentTimeMillis() / 1000)).intValue();
    }

    //判断现在的时间是8点之前函数8点之后
    public static boolean isBefore8AM() {

        //1.获取现在的时间
        Calendar cdr = getToday();
        int hour = cdr.get(Calendar.HOUR_OF_DAY);// 获取小时
        if (hour >= 0 && hour <= 8) {
            return true;
        }
        return false;
    }

    //判断现在的时间是9点之前函数9点之后
    public static boolean isBefore9AM() {

        //1.获取现在的时间
        Calendar cdr = getToday();
        int hour = cdr.get(Calendar.HOUR_OF_DAY);// 获取小时
        if (hour >= 0 && hour <= 9) {
            return true;
        }
        return false;
    }

    /**
     * 获得秒级时间戳
     *
     * @return 时间戳(秒)
     */
    public static int getSecTimestamp() {
        return ((Long) Instant.now().getEpochSecond()).intValue();
    }

    /**
     * 获得毫秒级时间戳
     *
     * @return 时间戳(毫秒)
     */
    public static long getMsTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获得今天0点0分0秒的秒级时间戳
     *
     * @return 时间戳(秒)
     */
    public static long getTodaySec0H0M0S() {
        Calendar calendar = getCalendar0H0M0S();
        return calendar.getTime().getTime() / 1000;
    }

    /**
     * 获得今天0点0分0秒的毫秒级时间戳
     *
     * @return 时间戳(毫秒)
     */
    public static long getTodayMs0H0M0S() {
        Calendar calendar = getCalendar0H0M0S();
        return calendar.getTime().getTime();
    }

    /**
     * 获得昨天0点0分0秒的毫秒级时间戳
     *
     * @return 时间戳(毫秒)
     */
    public static long getYesterdayMs0H0M0S() {
        Date yesterday = getYesterday0H0M0S0MS();
        return yesterday.getTime();
    }

    private static Calendar getCalendar0H0M0S() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    /**
     * 获得昨天Date（年月日）
     * System.out = "Wed Aug 01 00:00:00 CST 2018"
     */
    public static Date getYesterday0H0M0S0MS() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        ZonedDateTime zdt = yesterday.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获得今天Date（年月日）
     * System.out = "Thu Aug 02 00:00:00 CST 2018"
     */
    public static Date getToday0H0M0S0MS() {
        LocalDate today = LocalDate.now();
        ZonedDateTime zdt = today.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获得前天Date（年月日）
     * System.out = "Thu Aug 02 00:00:00 CST 2018"
     */
    public static Date getTheDayBeforeYesterday0H0M0S0MS() {
        LocalDate yesterday = LocalDate.now().minusDays(2);
        ZonedDateTime zdt = yesterday.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获得之前n天的Date（年月日）
     * System.out = "Thu Aug 02 00:00:00 CST 2018"
     */
    public static Date getBeforeNDays0H0M0S0MS(int n) {
        LocalDate yesterday = LocalDate.now().minusDays(n);
        ZonedDateTime zdt = yesterday.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获得元时间1970年01月01日08时00分01秒
     */
    public static Date getDefaultDate0H0M0S0MS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse("1970-01-01 08:00:01");
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断是否是昨天0时0分0秒0毫秒
     *
     * @return true:是，false:否
     */
    public static boolean isYesterday0H0M0S0MS(Date date) {
        if (date == null) {
            return false;
        }
        Date yesterday = getYesterday0H0M0S0MS();
        return yesterday.getTime() == date.getTime();
    }

    /**
     * 判断是否是昨天， 不关心时分秒等
     *
     * @return true:是，false:否
     */
    public static boolean isYesterday(Date date) {
        if (date == null) {
            return false;
        }
        Date yesterday = getYesterday0H0M0S0MS();
        return date.getTime() - yesterday.getTime() >= 0 && date.getTime() - yesterday.getTime() <= 86400000;
    }

    /**
     * 判断两个 Date 是否是同一天
     *
     * @return true:是，false:否
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        boolean isSameYear = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
        if (!isSameYear) {
            return false;
        }
        boolean isSameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
        if (!isSameMonth) {
            return false;
        }
        return calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    public static Date parse(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("parse time to  date error", e);
        }
        return null;
    }

    /**
     * 获取DATE凌晨
     *
     * @param date
     * @return
     */
    public static Date getDate0H0M0S0MS(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取DATE最后一秒
     *
     * @param date
     * @return
     */
    public static Date getDate23H59M59S0MS(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    public static boolean isValidDateYMD(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 求两个时间的时间间隔
     *
     * @param date1 开始时间
     * @param date2 结束时间
     * @return 秒数
     */
    public static long getDateInterval(Date date1, Date date2) {

        long date1Time = date1.getTime();
        long date2Time = date2.getTime();
        long interval = (date1Time - date2Time) / 1000;
        return interval;
    }

    /**
     * 获取一个小时对应的秒数
     *
     * @param hourStr 小时
     * @return 秒数
     */
    public static long getSecondsByHour(String hourStr) {

        try {
            double hour = Double.parseDouble(hourStr);
            double seconds = hour * 60 * 60;
            return (long) seconds;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 时间戳转换为Date类型
     *
     * @param stmp
     * @return
     * @throws Exception
     */
    public static Date getDatebyTimestmp(Long stmp) {
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = formatter2.format(stmp);
        Date parse = null;
        try {
            parse = formatter2.parse(d);
        } catch (ParseException e) {
            log.error("parse timestamp to date error");
        }
        return parse;
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDays(String date1, String date2) {
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        Date date = null;
        Date mydate = null;
        try {
            date = formatter2.parse(date1);
            mydate = formatter2.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return Integer.valueOf(String.valueOf(day));
    }

    /**
     * 获得 从现在开始向历史前移N个小时的Date对象
     * 比如现在是2018-11-21 15:03:24，那么前移2个小时，则会返回 2018-11-21 13:03:24
     *
     * @return
     */
    public static Date getUnShiftNHourDate(int n) {
        long shift = 3600000 * n;
        long target = System.currentTimeMillis() - shift;
        return new Date(target);
    }

    /**
     * 获取今天8点的时间
     *
     * @return
     */
    public static Date getToday8H0M0S() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 比较两个日期相差的小时数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diffHours(Date date1, Date date2) {

        long date1Time = date1.getTime();
        long date2Time = date2.getTime();

        int hours = (int) ((Math.abs(date1Time - date2Time)) / (1000 * 3600));
        return hours;
    }

    /**
     * 获得基于制定时刻的位移n小时的Date（年月日 时）
     * 例如:n=-1表示往后移一小时，n=1表示往前移一小时
     * 比如：2019-01-10 14:20:20 倒退1小时 = 2019-01-10 13:20:20
     * System.out = "Thu Jan 10 13:20:20 CST 2019"
     */
    public static Date getBeforeNHours(Date date, long n) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime minusHours = localDateTime.minusHours(n);
        ZonedDateTime zdt = minusHours.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            return day2 - day1;
        }
    }

    /**
     * Date转换为String
     * @param date
     * @param formatTemplate
     * @return
     */
    public static String date2String(Date date, String formatTemplate) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatTemplate);
        return formatter.format(date);

    }

    /**
     * 获取指定日期 的 零点时间戳
     * @param date 日期。如  "2020-04-12"
     * @return 时间戳 毫秒
     */
    public static Long getStartOfToday(String date) {
        if (StringUtils.isEmpty(date)) {
            date = date2String(new Date(), "yyyy-MM-dd");
        }
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
        LocalDate parse = LocalDate.parse(date, ofPattern);
        return parse.atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取指定日期 的 末尾时间戳
     * @param date 日期。如  "2020-04-12"
     * @return 时间戳 毫秒
     */
    public static Long getEndOfDay(String date) {
        if (StringUtils.isEmpty(date)) {
            date = date2String(new Date(), "yyyy-MM-dd");
        }
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
        LocalDate parse = LocalDate.parse(date, ofPattern);
        return LocalDateTime.of(parse, LocalTime.MAX).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }



    /**
     * 将字符串转日期成Long类型的时间戳，格式为：yyyy-MM-dd HH:mm:ss
     */
    public static Long convertTimeToLong(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(getAfterNHour0M0S0MS(new Date(), 0).getTime());
//        System.out.println(isValidDateYMD("2121-09-23"));
//        System.out.println("2121-09-23".compareTo(DateUtil.getBeforeDay(DateUtil.format(DateUtil.getToday()))));
//        System.out.println(DateUtil.getBeforeDay("2121-09-23"));
//        System.out.println(getUnShiftNHourDate(24));

//        System.out.println(LocalDate.now().plusDays(-1));

        Long time = convertTimeToLong("2020-01-05 00:00:00");
        System.out.println(time);
    }

}