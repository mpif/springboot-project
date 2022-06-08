package com.codefans.springboot.common;

/**
 * @Author: codefans
 * @Date: 2022-05-24 10:22
 */

public class DateUtils {

    public static long currentSecond() {
        long time = System.currentTimeMillis();
        return time/1000;
    }

}
