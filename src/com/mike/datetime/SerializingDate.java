package com.mike.datetime;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializingDate {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Date date = java.sql.Date.valueOf(LocalDate.of(2019, 1, 1));

        System.out.println("Date as string:" + mapper.writeValueAsString(date));
    }
}
