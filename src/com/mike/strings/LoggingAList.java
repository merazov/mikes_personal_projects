package com.mike.strings;

import java.util.ArrayList;
import java.util.List;

public class LoggingAList {

    public static void main(String[] args) {
        List<String> ids = new ArrayList<>();
        ids.add("a");
        ids.add("b");
        ids.add("c");
        
        System.out.println("list=" + ids);
        // prints: list=[a, b, c]
    }
}
