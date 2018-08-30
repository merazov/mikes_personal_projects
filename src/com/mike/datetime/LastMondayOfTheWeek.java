package com.mike.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LastMondayOfTheWeek {

    public static void main(String[] args) {
        LocalDate ld = LocalDate.of(2018, 8, 26); // Friday as original date
        LocalDate monday = ld.with(DayOfWeek.MONDAY);

        System.out.println(ld.with(DayOfWeek.MONDAY)); // 2017-08-20 (2 days later according to ISO)
        if (monday.isAfter(ld)) {
            System.out.println(ld.with(DayOfWeek.MONDAY).minusDays(7)); // 2017-08-20 (2 days later according to ISO)
        }
    }
}
