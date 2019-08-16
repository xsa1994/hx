package com.hx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);
    private static final SimpleDateFormat DAY = getFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DAY_NUMBER = getFormat("yyyyMMdd");
    private static final SimpleDateFormat YEAR_DAY_NUMBER = getFormat("yyMMdd");
    private static final SimpleDateFormat YEAR = getFormat("yyyy");
    private static final SimpleDateFormat DAY_ONLY = getFormat("MM-dd");
    private static final SimpleDateFormat SECOND = getFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SECOND_ONLY = getFormat("HH:mm:ss");
    private static final SimpleDateFormat MINUTE = getFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat MINUTE_ONLY = getFormat("HH:mm");
    private static final SimpleDateFormat MINUTE_ANOTHER = getFormat("yyyyMMdd-HHmm");
    private static final SimpleDateFormat MONTH_DAY_YEAR = getFormat("MM/dd/yyyy");
    private static final SimpleDateFormat MILLI_SECOND = getFormat("yyyyMMddHHmmssSSS");

    public DateUtils() {
    }

    public static String getYearStr(Date date) {
        SimpleDateFormat var1 = YEAR;
        synchronized (YEAR) {
            return getStr(date, YEAR);
        }
    }

    public static String getYearStr(long date) {
        SimpleDateFormat var2 = YEAR;
        synchronized (YEAR) {
            return getStr(new Date(date), YEAR);
        }
    }

    public static String getMinuteDbStr(Date date) {
        SimpleDateFormat var1 = MINUTE_ANOTHER;
        synchronized (MINUTE_ANOTHER) {
            return getStr(date, MINUTE_ANOTHER);
        }
    }

    public static Date getMinuteDbDate(String str) {
        SimpleDateFormat var1 = MINUTE_ANOTHER;
        synchronized (MINUTE_ANOTHER) {
            return getDate(str, MINUTE_ANOTHER);
        }
    }

    public static Date getMonDayYearDate(String str) {
        SimpleDateFormat var1 = MONTH_DAY_YEAR;
        synchronized (MONTH_DAY_YEAR) {
            return getDate(str, MONTH_DAY_YEAR);
        }
    }

    public static String getSecondOnlyStr(Date date) {
        SimpleDateFormat var1 = SECOND_ONLY;
        synchronized (SECOND_ONLY) {
            return getStr(date, SECOND_ONLY);
        }
    }

    public static String getSecondOnlyStr(long date) {
        return getSecondOnlyStr(new Date(date));
    }

    public static String getOnlyDayStr(long date) {
        SimpleDateFormat var2 = DAY_ONLY;
        synchronized (DAY_ONLY) {
            return getStr(new Date(date), DAY_ONLY);
        }
    }

    public static String getSecondStr(long date) {
        return getSecondStr(new Date(date));
    }

    public static String getSecondStr(Date date) {
        SimpleDateFormat var1 = SECOND;
        synchronized (SECOND) {
            return getStr(date, SECOND);
        }
    }

    public static String getMinuteStr(Date date) {
        SimpleDateFormat var1 = MINUTE;
        synchronized (MINUTE) {
            return getStr(date, MINUTE);
        }
    }

    public static String getMinuteStr(long date) {
        return getMinuteStr(new Date(date));
    }

    public static String getMinuteOnlyStr(Date date) {
        SimpleDateFormat var1 = MINUTE_ONLY;
        synchronized (MINUTE_ONLY) {
            return getStr(date, MINUTE_ONLY);
        }
    }

    public static String getDayStr(Date date) {
        SimpleDateFormat var1 = DAY;
        synchronized (DAY) {
            return getStr(date, DAY);
        }
    }

    public static String getDayStr(long date) {
        return getDayStr(new Date(date));
    }

    public static int getDayNumber(Date date) {
        if (date == null) {
            return 0;
        } else {
            SimpleDateFormat var1 = DAY_NUMBER;
            synchronized (DAY_NUMBER) {
                return Integer.valueOf(getStr(date, DAY_NUMBER)).intValue();
            }
        }
    }

    public static int getYYDayNumber(Date date) {
        if (date == null) {
            return 0;
        } else {
            SimpleDateFormat var1 = YEAR_DAY_NUMBER;
            synchronized (YEAR_DAY_NUMBER) {
                return Integer.valueOf(getStr(date, YEAR_DAY_NUMBER)).intValue();
            }
        }
    }

    public static Date getDayDate(Date date) {
        return getDayDate(getDayStr(date));
    }

    public static Date getSecondDate(String dateStr) {
        SimpleDateFormat var1 = SECOND;
        synchronized (SECOND) {
            return getDate(dateStr, SECOND);
        }
    }

    public static Date getDayDate(String dateStr) {
        SimpleDateFormat var1 = DAY;
        synchronized (DAY) {
            return getDate(dateStr, DAY);
        }
    }

    public static Date getMinuteOnlyDate(String dateStr) {
        SimpleDateFormat var1 = MINUTE_ONLY;
        synchronized (MINUTE_ONLY) {
            return getDate(dateStr, MINUTE_ONLY);
        }
    }

    public static Date getMinuteDate(String dateStr) {
        SimpleDateFormat var1 = MINUTE;
        synchronized (MINUTE) {
            return getDate(dateStr, MINUTE);
        }
    }

    public static Date getMinuteDate(long time) {
        return getMinuteDate(getMinuteStr(time));
    }

    public static long getMiniteDate(Date date, String str) {
        if (str == null) {
            return 0L;
        } else {
            Date date1 = getMinuteDate(getDayStr(date) + " " + str);
            return date1 == null ? 0L : date1.getTime();
        }
    }

    public static Date monthsAddOrSub(Date date, int offset) {
        return addOrSub(date, 2, offset);
    }

    public static Date daysAddOrSub(Date date, int offset) {
        return addOrSub(date, 5, offset);
    }

    public static Date hoursAddOrSub(Date date, int offset) {
        return addOrSub(date, 11, offset);
    }

    public static Date minutesAddOrSub(Date date, int offset) {
        return addOrSub(date, 12, offset);
    }

    public static Date secondsAddOrSub(Date date, int offset) {
        return addOrSub(date, 13, offset);
    }

    public static int compareHHmmInString(String ts1, String ts2) {
        return ts1.compareTo(ts2);
    }

    public static int betweenHHmmInString(String ts, String start, String end) {
        if (compareHHmmInString(start, end) >= 0) {
            return -1;
        } else if (compareHHmmInString(ts, start) < 0) {
            return 0;
        } else {
            return compareHHmmInString(end, ts) <= 0 ? 0 : 1;
        }
    }

    public static boolean equalsInTimeString(String ts1, String ts2) {
        if (ts1.equals(ts2)) {
            return true;
        } else {
            return ("00:00".equals(ts1) || "24:00".equals(ts1)) && ("00:00".equals(ts2) || "24:00".equals(ts2));
        }
    }

    public static String getMillisecond() {
        SimpleDateFormat var0 = MILLI_SECOND;
        synchronized (MILLI_SECOND) {
            return getStr(new Date(), MILLI_SECOND);
        }
    }

    public static int compareHHmm(Calendar cal, Calendar c) {
        if (cal.get(11) > c.get(11)) {
            return 1;
        } else if (cal.get(11) == c.get(11)) {
            if (cal.get(12) > c.get(12)) {
                return 1;
            } else {
                return cal.get(12) == c.get(12) ? 0 : -1;
            }
        } else {
            return -1;
        }
    }

    public static int betweenHHmm(Calendar cal, Calendar start, Calendar end) {
        if (compareHHmm(start, end) != -1) {
            return -1;
        } else if (compareHHmm(cal, start) == -1) {
            return 0;
        } else {
            return compareHHmm(end, cal) == -1 ? 0 : 1;
        }
    }

    public static boolean compareDay(Calendar cal, Calendar c) {
        return cal.get(1) == c.get(1) && cal.get(2) == c.get(2) && cal.get(5) == c.get(5);
    }

    public static String calendar2TimeString(Calendar cal) {
        return (cal.get(11) > 9 ? cal.get(11) : "0" + cal.get(11)) + ":" + (cal.get(12) > 9 ? cal.get(12) : "0" + cal.get(12));
    }

    public static int getHour(String timeString) {
        return Integer.parseInt(timeString.substring(0, 2));
    }

    public static int getMinute(String timeString) {
        return Integer.parseInt(timeString.substring(3, 5));
    }

    public static String getDateOnlyFromDate(String dateString) {
        return dateString.substring(5, 10);
    }

    public static String calendar2DateString(Calendar cal) {
        return (cal.get(2) + 1 > 9 ? cal.get(2) + 1 : "0" + (cal.get(2) + 1)) + "-" + (cal.get(5) > 9 ? cal.get(5) : "0" + cal.get(5));
    }

    public static Integer monthSub(Date beginDate, Date endDate) {
        Integer monthNum = Integer.valueOf(0);
        Integer yearNumber = Integer.valueOf(0);
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(beginDate);
        endCalendar.setTime(endDate);
        yearNumber = endCalendar.get(1) - startCalendar.get(1);
        monthNum = endCalendar.get(2) - startCalendar.get(2);
        return yearNumber.intValue() * 12 + monthNum.intValue();
    }

    public static Date getMonthDayByIndex(Date beginDate, int index) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.set(5, index);
        return cal.getTime();
    }

    public static Map<String, Date> retTodayAndTomorrow() {
        Map<String, Date> dateMap = new HashMap();
        Date start = getDayDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(5, 1);
        Date end = cal.getTime();
        dateMap.put("startDay", start);
        dateMap.put("endDay", end);
        return dateMap;
    }

    public static int getToTomorrowSeconds() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate.get(1), curDate.get(2), curDate.get(5) + 1, 0, 0, 0);
        return Math.max((int) ((tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000L), 1);
    }

    public static long getTodayZeroTimeInMillis() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTimeInMillis();
    }

    public static long getMonthFirstDayTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long getNextMonthFirstDayTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long getDifferDay(Date date) {
        Date nowDate = new Date();
        return nowDate.after(date) ? 0L : Math.abs(date.getTime() - nowDate.getTime()) / 86400000L + (long) (Math.abs(date.getTime() - nowDate.getTime()) % 86400000L == 0L ? 0 : 1);
    }

    private static Date addOrSub(Date date, int type, int offset) {
        if (date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.get(type);
            cal.set(type, cal.get(type) + offset);
            return cal.getTime();
        }
    }

    private static String getStr(Date date, SimpleDateFormat format) {
        return date == null ? "" : format.format(date);
    }

    private static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    private static Date getDate(String dateStr, SimpleDateFormat format) {
        if (!"".equals(dateStr) && dateStr != null) {
            try {
                return format.parse(dateStr);
            } catch (ParseException var3) {
                LOG.error("format yyyy-MM-dd HH:mm:ss error:", var3);
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date getMondayDate(Date day) {
        Calendar calWeek = Calendar.getInstance();
        calWeek.setTime(day);
        if (1 == calWeek.get(7)) {
            calWeek.add(5, -6);
        } else if (2 == calWeek.get(7)) {
            calWeek.add(5, -7);
        } else if (3 == calWeek.get(7)) {
            calWeek.add(5, -1);
        } else if (4 == calWeek.get(7)) {
            calWeek.add(5, -2);
        } else if (5 == calWeek.get(7)) {
            calWeek.add(5, -3);
        } else if (6 == calWeek.get(7)) {
            calWeek.add(5, -4);
        } else if (7 == calWeek.get(7)) {
            calWeek.add(5, -5);
        }

        return getDayDate(calWeek.getTime());
    }

    public static Date getFirstDayDate(Date day) {
        Calendar calMonth = Calendar.getInstance();
        calMonth.setTime(day);
        if (1 == calMonth.get(5)) {
            calMonth.setTime(daysAddOrSub(day, -1));
        }

        calMonth.set(5, 1);
        return getDayDate(calMonth.getTime());
    }

    public static Date getStartTime(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            return calendar.getTime();
        }
    }

    public static Date getEndTime(Date date) {
        if (null == date) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            return calendar.getTime();
        }
    }

    public static Date getEndTime(String strDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDayDate(strDate));
        c.set(11, 23);
        c.set(12, 59);
        c.set(13, 59);
        return c.getTime();
    }

    public static Date getStartTime(String strDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(getDayDate(strDate));
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        return c.getTime();
    }

    public static int daysBetween(Date smdate, Date bdate) {
        smdate = getDayDate(smdate);
        bdate = getDayDate(bdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static Date changeByDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, day);
        return cal.getTime();
    }

    public static String getYesterday() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        date.set(5, date.get(5) - 1);
        Date endDate = null;

        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException var4) {
            throw new RuntimeException(var4);
        }

        return dft.format(endDate);
    }
}
