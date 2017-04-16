package xuyihao.JohnsonTest;

import xuyihao.JohnsonTest.utils.DateUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xuyh at 2017/3/12 12:45.
 */
public class DateTestMain {
    private static long getLastFourHourMilliTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        return calendar.getTime().getTime();
    }

    private static Date getLastFourHourDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        return calendar.getTime();
    }

    public static void main(String...args){
        Date timeFourHourAgo = getLastFourHourDateTime();
        System.out.println(timeFourHourAgo);
    }
}
