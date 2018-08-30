package com.mike.utils;

import org.apache.commons.lang3.ObjectUtils;

public class TestingObjectUtils {

    public static void main(String[] args) {
        org.junit.Assert.assertEquals(-1, ObjectUtils.compare(new Integer(1), new Integer(2)));
        org.junit.Assert.assertEquals(0, ObjectUtils.compare(new Integer(2), new Integer(2)));
        org.junit.Assert.assertEquals(-1, ObjectUtils.compare(null, new Integer(2)));
        org.junit.Assert.assertEquals(0, ObjectUtils.compare(null, null));
    }
}
