package org.example.date;

import java.time.LocalDate;

/**
 * TODO
 *
 * @author MGG
 * @version 1.0
 * @date 2021-04-14 11:34 AM
 */
public class Main3 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalDate date1 =  date.withDayOfMonth(2);
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getMonthValue()+"-"+date1.getDayOfMonth());
    }
}
