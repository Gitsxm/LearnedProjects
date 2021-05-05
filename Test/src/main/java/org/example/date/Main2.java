package org.example.date;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO 这个月到下个月 1 号相差几天
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-24 3:18 PM
 */
public class Main2 {
    public static void main(String[] args) {
        Date date = new Date();
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        //设置为 1 号
        today.set(Calendar.DAY_OF_MONTH, 1);
        //下个月
        today.add(Calendar.MONTH, 1);
        Calendar nextMonth1th = Calendar.getInstance();
        nextMonth1th.setTime(today.getTime());
        //重新设置本月
        today.setTime(date);
        System.out.println(nextMonth1th.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) - 1);
        System.out.println("jdk8 版本：");
        jdk8Mode();
    }

    public static void jdk8Mode() {
        LocalDate today = LocalDate.now();
        //下个月一号的 LocalDate 格式
        System.out.println(today.plusMonths(1).withDayOfMonth(1));
        //相差天数 这里不带今天了 带见天就不要 -1 了
        System.out.println(today.plusMonths(1).withDayOfMonth(1).toEpochDay() - today.toEpochDay() - 1);
    }

}
