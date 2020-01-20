package com.mike.utils;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class CollectionUtilsForNullObjects {

    public static void main(String[] args) {
        List<Integer> listA = Arrays.asList(null,6,5);
        List<Integer> listB = Arrays.asList(5,null,6);
        assertTrue(CollectionUtils.isEqualCollection(listA, listB));

        System.out.println("comp1=" + CollectionUtils.isEqualCollection(listA, listB));
    }
}
