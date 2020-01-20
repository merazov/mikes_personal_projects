package com.mike.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTests {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate.parse("2019-04-05", formatter);
    }
}
