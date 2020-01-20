package com.mike.time;


import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;


public class ISODateTimeFormatTester {

    public static void main(String[] args) {
        System.out.println(ISODateTimeFormat.weekyearWeek().print(LocalDate.now()));
    }
}
