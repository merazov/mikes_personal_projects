package com.mike.strings;

import org.apache.commons.lang3.StringUtils;

public class TestingSubStrings {

    public static void main(String[] args) {
        String str = "mike";
        /*str = str.substring(0, 1000);
        System.out.println("str=" + str);*/ //<throws exception
        
        String newStr = StringUtils.substring(str, 0, 1000);
        System.out.println("newStr=" + newStr);
    }
}
