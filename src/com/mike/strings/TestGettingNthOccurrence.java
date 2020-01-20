package com.mike.strings;

import org.apache.commons.lang3.StringUtils;

public class TestGettingNthOccurrence {

    public static void main(String[] args) {
        final int indexOfOccurenceOfInterest = 3; // < all models 
        int index = StringUtils.ordinalIndexOf("us_online_tablet", "_", indexOfOccurenceOfInterest);
        System.out.println("index=" + index);
        
        String s3Path = "arn:aws:s3:::/orchestrationdataquerytask".split(":")[5];
        System.out.println("s3Path=" + s3Path);
        
        s3Path = "arn:aws:s3:::awsscm-test-resources/orchestrationdataquerytask".split(":")[5];
        System.out.println("s3Path=" + s3Path);
    }
}
