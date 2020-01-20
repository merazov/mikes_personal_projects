package com.mike.strings;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

public class RegularExpressionsWith$ {

    public static final String INSTANCE_ID_REGEX = "[a-z0-9]+";

    public static final String WHATEVER = "^" + INSTANCE_ID_REGEX + "$";
    
    private static final Pattern PATTERN = Pattern.compile(WHATEVER);
    
    public static final String INSTANCE_ID_REGEX_2 = "^[a-z0-9]+$";
    
    public static void main(String[] args) {
        assertTrue(PATTERN.matcher("abc").matches()); //works
        
        assertTrue(Pattern.compile(INSTANCE_ID_REGEX_2).matcher("abc").matches());
    }
}
