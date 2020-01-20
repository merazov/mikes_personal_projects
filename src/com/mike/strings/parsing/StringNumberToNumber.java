package com.mike.strings.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

public class StringNumberToNumber {

    private static final Pattern BUILD_ID_PATTERN = Pattern
            .compile("(?<siteName>[A-Z]{3}[A-Z0-9]{3}(_ST)?)(?<buildType>.{3})(?<buildSeqPlaceHolder>[A-PZ]\\d{1,3}|[A-Z]{3})(?:\\.(?<phaseNumber>\\d{2,3}))?");
    private static final Pattern BUILD_SEQ_PATTERN = Pattern.compile("[A-P](\\d{1,3})");

    public static void main(String[] args) {
        
        Matcher matcher = BUILD_ID_PATTERN.matcher(StringUtils.defaultIfBlank("ARN050INFP01.001", ""));
        if (matcher.matches()) {
            System.out.println("sequence=" + matcher.group("buildSeqPlaceHolder"));//< sequence=P01
        } else {
            System.out.println("no match");
        }
        
        matcher = BUILD_SEQ_PATTERN.matcher("P012");
        if (matcher.matches()) {
            System.out.println("sequence as number=" + NumberUtils.toInt(matcher.group(1))); //<sequence as number=12
        } else {
            System.out.println("no match");
        }
    }
}
