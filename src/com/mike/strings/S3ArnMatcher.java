package com.mike.strings;

import java.util.regex.Pattern;

import org.apache.commons.lang3.Validate;

public class S3ArnMatcher {

    private static final String S3_ARN_REGEX = "^arn:(aws|aws-cn):s3:([a-z]{2}-[a-z]{4,9}-[1-9])?:[1-9]*:[A-Za-z0-9-.]+\\/[A-Za-z0-9-.]+$";
    
    private static final Pattern S3_ARN_PATTERN = Pattern.compile(S3_ARN_REGEX);
    
    public static void main(String[] args) {
        Validate.isTrue(S3_ARN_PATTERN.matcher("arn:aws:s3:::awsscm-test-resources/orchestrationdataquerytask").matches(), "ARN does not match valid syntax-1");
        //Validate.isTrue(S3_ARN_PATTERN.matcher("arn:aws:s3:::").matches(), "ARN does not match valid syntax-2"); //< explodes
        Validate.isTrue(S3_ARN_PATTERN.matcher("arn:aws:s3::3:awsscm-test-resources/orchestrationdataquerytask").matches(), "ARN does not match valid syntax-2"); //< explodes
        //Validate.isTrue(S3_ARN_PATTERN.matcher("arn:aws:s3::3:awsscm-test-resources").matches(), "ARN does not match valid syntax-2"); //< explodes
    }
}
