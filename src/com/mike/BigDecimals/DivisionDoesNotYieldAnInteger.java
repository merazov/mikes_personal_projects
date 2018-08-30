package com.mike.BigDecimals;

import java.math.BigDecimal;

public class DivisionDoesNotYieldAnInteger {

    public static void main(String[] args) {
        BigDecimal num = new BigDecimal(50005000);
        //BigDecimal num = new BigDecimal("50005000");

        System.out.println(num.divide(new BigDecimal(10000)));
        System.out.println(num.divide(new BigDecimal(10000)).floatValue());
        System.out.println(num.divide(new BigDecimal(10000)).doubleValue());
    }
}
