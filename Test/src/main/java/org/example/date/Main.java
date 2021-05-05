package org.example.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO 日期比较
 *
 * @author MGG
 * @version 1.0
 * @date 2021-03-17 2:41 PM
 */
public class Main {
    public static void main(String[] args) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date d = new Date();
        c1.setTime(d);
        c2.setTime(d);
        c1.add(Calendar.MINUTE, -120);
        System.out.println(c1.getTime().getTime() > c2.getTime().getTime());
        System.out.println("jdk8 类似比较方法：");
        jdk8Mode();
    }

    /**
     * jdk8 版本
     */
    public static void jdk8Mode() {
        LocalDate b = LocalDate.now();
        LocalDate e = LocalDate.now().plusDays(3);
        System.out.println(b);
        System.out.println(e);
        System.out.println(b.isAfter(e));
        System.out.println(b.isBefore(e));
        System.out.println(b.isEqual(e));
    }
}
