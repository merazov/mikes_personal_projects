package com.mike.zero;

public class TestingZero {

    public static void main(String[] args) {
        double x = 1/0.0000000000000000000000000001;
        System.out.println(x); //100000000
        
        long sebastian = 65*737;
        System.out.println(sebastian);
    }
}
