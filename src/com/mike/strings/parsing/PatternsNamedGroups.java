package com.mike.strings.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsNamedGroups {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(?<login>\\w+) (?<id>\\d+)");

        Matcher matcher = pattern.matcher("TEST 123");

        System.out.println("login=" + matcher.matches());
        System.out.println("login=" + matcher.group(1));
        System.out.println("login=" + matcher.group(2));
        System.out.println("login=" + matcher.group("login"));
        System.out.println("login=" + matcher.group("id"));
        System.out.println("login=" + matcher.group("xxx"));
        /*
         *  login=true
            login=TEST
            login=123
            login=TEST
            login=123
            Exception in thread "main" java.lang.IllegalArgumentException: No group with name <xxx>
                at java.util.regex.Matcher.getMatchedGroupIndex(Matcher.java:1316)
                at java.util.regex.Matcher.group(Matcher.java:572)
                at com.mike.strings.parsing.PatternsNamedGroups.main(PatternsNamedGroups.java:18)
         */
    }
}
