package com.amazon.functionalprogrammingstructures;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestingFlatMap {

    public static void main(String[] args) {
        String[][] data = new String[][] { { "a", "b" }, { "c", "d" }, { "e", "f" } };

        // Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);
        
        // Stream<String>, GOOD!
        Stream<String> stringStream = temp.flatMap(x -> Arrays.stream(x));
        //stringStream.forEach(System.out::println);
        /*
         *  a
            b
            c
            d
            e
            f
         */

        Stream<String> stream = stringStream.filter(x -> "a".equals(x.toString()));

        stream.forEach(System.out::println);
    }
}
