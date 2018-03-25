package com.mike.datastructures;

import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

public class RangeMaps {

    public static void main(String[] args) {
        RangeMap<Integer, String> experienceRangeDesignationMap = TreeRangeMap.create();
        experienceRangeDesignationMap.put(Range.closed(0, 2), "Associate");
        experienceRangeDesignationMap.put(Range.closed(0, 3), "mike"); //<overriding previous value
        Preconditions.checkArgument(experienceRangeDesignationMap.get(1).equals("mike"));
    }
}
