package com.example.tuionf.a24hourstimer.util;

import android.icu.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tuion on 2017/8/24.
 */

public class DateUtil {

    public static Calendar cal;
    /*获取当前系统时间  分钟形式*/
    public static Long getSysDate(){

        cal = Calendar.getInstance();
        return cal.getTimeInMillis();
    }

    /*获取当前系统时间  HH:mm 形式 */
    public static String getSysDate1(){
        SimpleDateFormat   formatter   =   new   SimpleDateFormat("HH:mm");
        Date curDate =  new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    public static String getSysTimeDay(){
        SimpleDateFormat   formatter   =   new   SimpleDateFormat("MM月dd日");
        Date curDate =  new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }
    /*格式化日期时间*/
    public static String formatDatetime(long date){
        Date date1 = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(date1);
    }
}
