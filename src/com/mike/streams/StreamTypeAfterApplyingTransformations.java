package com.mike.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Value;

public class StreamTypeAfterApplyingTransformations {

    @Value
    class Person {
        private final String id;
    }

    public static void main(String[] args) {
        List<Person> mypersons = new ArrayList<>();

        mypersons.stream()
                .map(person -> person.id) // < Stream<String>
                .map(id -> 1) // < Stream<Integer>
                .collect(Collectors.toList()); // <List<Integer>

        final Double[] doubleArray = new Double[10];
        Arrays.asList(doubleArray)
                .stream()
                .map(number -> (int) Math.ceil(number))
                .collect(Collectors.toList());
    }
}
