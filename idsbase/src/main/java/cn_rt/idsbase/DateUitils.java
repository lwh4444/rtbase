package cn_rt.idsbase;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

;

/**
 * Created by ${zml} on 2018/2/28.
 */
public class DateUitils {
    private static SimpleDateFormat format;
    private static long currentTime;
    private static String[] time;

    /**
     * 获取小时
     */
    public static String getCurrentHour() {
        currentTime = System.currentTimeMillis();
        format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        time = format.format(currentTime).split("-");
        return time[3];
    }

    /**
     * 获取分钟
     */
    public static String getCurrentMinute() {
        currentTime = System.currentTimeMillis();
        format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        time = format.format(currentTime).split("-");
        return time[4];
    }

    /**
     * 获取秒数
     */
    public static String getCurrentSecond() {
        currentTime = System.currentTimeMillis();
        format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        time = format.format(currentTime).split("-");
        return time[5];
    }

    public static int getWeeked() {
        int num = 0;
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
       /* mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份*//*
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码*/

        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));//获取星期的号码
        if ("1".equals(mWay)) {
            num = 7;
        } else if ("2".equals(mWay)) {
            num = 1;
        } else if ("3".equals(mWay)) {
            num = 2;
        } else if ("4".equals(mWay)) {
            num = 3;
        } else if ("5".equals(mWay)) {
            num = 4;
        } else if ("6".equals(mWay)) {
            num = 5;
        } else if ("7".equals(mWay)) {
            num = 6;
        }
        return num;
    }

    //判断当前时间是否在某一个时间范围内
    public static boolean belongCalendar(Date time, Date from, Date to) {
        if (time.getTime() == from.getTime()
                || time.getTime() == to.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);

        Calendar before = Calendar.getInstance();
        before.setTime(to);

        if (date.after(after) && date.before(before)) {
            return true;
        } else {
            return false;
        }
    }


    //判断某一个日期是否已经超过今天的日期
    public static boolean judgeCalendar(Date time, Date from) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);

        Calendar after = Calendar.getInstance();
        after.setTime(from);


        if (date.after(after)) {
            return true;
        } else {
            return false;
        }
    }

    //将一个时间的int类型转化为date类型
    public static Date intToDate(long seconds) throws ParseException {
        Date parse = null;
       /* Date date = new Date(seconds*1000);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(gregorianCalendar.getTime());
        System.out.println(format);
        return  format;*/
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(seconds * 1000);
        String time = format.format(date);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = formats.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }

    //将一个时间的int类型转化为date类型
    public static Date intToDateJohnson(long seconds) throws ParseException {
        Date parse = null;
       /* Date date = new Date(seconds*1000);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(gregorianCalendar.getTime());
        System.out.println(format);
        return  format;*/
        if (String.valueOf(seconds).length() == 10) {
            seconds = seconds * 1000;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(seconds);
        String time = format.format(date);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = formats.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }


    //将一个时间的int类型转化为date类型
    public static Date intToDateEnd(long seconds) throws ParseException {
        Date parse = null;
       /* Date date = new Date(seconds*1000);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(gregorianCalendar.getTime());
        System.out.println(format);
        return  format;*/
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(seconds);
        String time = format.format(date);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            parse = formats.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }


    //获取当前系统日期
    public static Date getCurrentDate() {
        Date parse = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = new Date();

        String format = df.format(date);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = formats.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    //获取当前系统时分时间
    public static String getCurrentSingleTime() {
        Date parse = null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        Date date = new Date();
        String format = df.format(date);

        return format;
    }

    //获取当前系统具体时间
    public static Date getCurrentTime() {
        Date parse = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date date = new Date();
        String format = df.format(date);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            parse = formats.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    //获取当前系统具体时间
    public static String getCurrentStringDate() {
        Date parse = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
        Date date = new Date();
        String format = df.format(date);

        return format;
    }

    //检查星期循环的范围内是否包含今天
    public static boolean isContain(int currentWeek, String dates) {
        int count = 0;
        if (!TextUtils.isEmpty(dates)) {
            String[] split = dates.split(",");

            if (split.length != 0) {
                for (int i = 0; i < split.length; i++) {
                    int week = Integer.parseInt(split[i]);
                    if (week == currentWeek) {
                        count += 1;
                    }
                }
            }
        }

        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    //比较两个时间大小
    public static int compare_date(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dt1 = null;
        try {
            dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 2;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



      /*  DateFormat df = new SimpleDateFormat("hh:mm:ss");
        try {
            java.util.Date d1 = df.parse(date1);
            java.util.Date d2 = df.parse(date2);
            if (d1.before(d2))
            {

                return 1;
            }
            else if (d1.after(d2))
            {

                return -1;
            }
            else
            {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }*/
        return 0;

    }

    //比较两个日期得大小
    public static int compareDateTime(Date currentData, Date datas) {

        if (currentData.getTime() > datas.getTime()) {
            return 1;
        } else if (currentData.getTime() < datas.getTime()) {
            return 2;
        } else {
            return 0;
        }
    }

    //获取出来的可用内存大小处理成mb
    public static long getAviMemory() {
        String s = ReadSystemMemory.showROMAvail();
        long availStroge = 0;

        if (s.contains("MB")) {
            String[] mbs = s.split("MB");
            String mb = mbs[0];
            if (mb.contains(",")) {
                String[] split = mb.split(",");
                availStroge = Integer.parseInt(split[0] + split[1]);
            } else {
                availStroge = Integer.parseInt(mb);
            }

        }
        return availStroge;
    }

    public static long getNormalTime() {
        String time = "2020-01-01 00:00:00";
        long times = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
            times = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;

    }


    //比较两个时间大小
    public static boolean isBefore(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);

            return dt1.before(dt2);

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 拼接按时节目得日期和时间
     *
     * @param startDateL
     * @param startTime
     */
    public static long jointDate(long startDateL, String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(startDateL * 1000);
        String dates = format + " " + startTime;
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = sdfs.parse(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timeStart = parse.getTime();
        return timeStart;
    }


    /**
     * 日期字符串转换日期类，用于定时任务
     *
     * @param dateL
     * @return
     */
    public static Date StringToDate(String dateL) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = sdf.parse(dateL);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }

    /**
     * 用户插播和定时
     */
    public static boolean isNowTimeMeeting(long startTimeL, long endTimeL) {
        try {
            Date startTime = DateUitils.intToDateEnd(startTimeL);
            Date endTime = DateUitils.intToDateEnd(endTimeL);
            Date currentDate = DateUitils.getCurrentTime();
            return !currentDate.before(startTime) && currentDate.before(endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 用于定时
     */

    public static boolean isNowTiming(long startDateL, long endDateL, String startTime, String endTime, int timerType) {

        if (timerType == Global.CAR_NORMAL) {
            //正常选择时间
            try {
                Date startDate = intToDate(startDateL);
                Date endDate = intToDate(endDateL);
                Date currentDate = getCurrentDate();

                if (startDate == null || endDate == null || currentDate == null) return false;
                //判断是否是今天
                boolean isToday = DateUitils.belongCalendar(currentDate, startDate, endDate);
                if (!isToday) return false;

                String currentSingleTime = DateUitils.getCurrentSingleTime();
                boolean isBeforeStart = DateUitils.isBefore(currentSingleTime, startTime);
                boolean isBeforeEnd = DateUitils.isBefore(currentSingleTime, endTime);
                return !isBeforeStart && isBeforeEnd;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //连续选择得时间日期
            try {
                long startTimer = DateUitils.jointDate(startDateL, startTime);
                long ensTimer = DateUitils.jointDate(endDateL, endTime);
                Date startTimes = DateUitils.intToDateEnd(startTimer);
                Date endTimes = DateUitils.intToDateEnd(ensTimer);
                Date currentDate = DateUitils.getCurrentTime();
                return !currentDate.before(startTimes) && currentDate.before(endTimes);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;

        }

        return false;
    }

}
