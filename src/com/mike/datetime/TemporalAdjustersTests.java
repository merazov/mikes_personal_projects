package com.mike.datetime;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjustersTests {

    private static final TemporalAdjuster ONE_WEEK_LESS_ADJUSTER = TemporalAdjusters.ofDateAdjuster(temporal -> temporal.minusWeeks(1L));
    
    public static void main(String[] args) {
        final LocalDate lastSunday = LocalDate.of(2018, 8, 26);

        System.out.println(lastSunday.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(lastSunday.with(ONE_WEEK_LESS_ADJUSTER));
        System.out.println(lastSunday.with(TemporalAdjusters.firstDayOfMonth()).with(ONE_WEEK_LESS_ADJUSTER));
    }
}
