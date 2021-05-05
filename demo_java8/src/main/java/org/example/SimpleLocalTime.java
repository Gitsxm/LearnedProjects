package org.example;

import java.time.*;

/**
 * 日期时间 API
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/2 23:31
 */
public class SimpleLocalTime {
    public static void main(String[] args) {
        //获取当前日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime); //2020-12-02T23:33:24.657  通过 .toString().replace("T"," ").substring(0,19) 获得常用的时间格式。
        //获取日时间
        LocalDate date = LocalDate.now();
        System.out.println(date); //2020-12-02
        //获取时间
        LocalTime time = LocalTime.now();
        System.out.println(time); //15:54:00.032
        //获取月
        Month month = currentTime.getMonth();
        System.out.println(month);//DECEMBER  枚举类型通过 getValue 获取数字
        //获取日（月单位）
        int dayOfMonth = currentTime.getDayOfMonth();
        System.out.println(dayOfMonth); //2
        //获取日（年单位）
        int dayOfYear = currentTime.getDayOfYear();
        System.out.println(dayOfYear);//337
        //获取当前秒
        int sec = currentTime.getSecond();
        System.out.println(sec);//28
        //获取特定日期time 年月日时分秒都可以设置
        LocalDateTime time1 = currentTime.withDayOfMonth(10).withYear(2019);
        System.out.println(time1);//2019-12-10T23:43:31.854
        //获取特定日期 年日月都可以设置
        LocalDate someDate = LocalDate.of(2010,Month.AUGUST,28);
        System.out.println(someDate);//2010-08-28
        //获取特定Time 时分秒甚至毫秒 都可以设置
        LocalTime localTime = LocalTime.of(23,30);
        System.out.println(localTime);//23:30
        //解析字符串 注意严格按照标准时间格式，改补的零都补上。
        LocalTime parseTime = LocalTime.parse("21:01");
        System.out.println(parseTime);//21:01

        /*时区设置**************************************/
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);//2015-12-03T10:15:30+08:00[Asia/Shanghai]

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);//Europe/Paris

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);//Asia/Shanghai

    }
}
