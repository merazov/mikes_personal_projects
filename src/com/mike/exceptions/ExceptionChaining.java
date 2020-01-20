package com.mike.exceptions;

public class ExceptionChaining {

    public static void third() {
        throw new Third("third");
    }

    public static void second() {
        try {
            third();
        } catch (Exception e) {
            if (e.getCause() != null) {
   //             System.out.println("***cause at second:" + e.getCause().getMessage());
            }
            throw new Second("second", e);
        }
    }

    public static void first() {
        try {
            second();
        } catch (Exception e) {
            if (e.getCause() != null) {
    //          System.out.println("***cause at first:" + e.getCause().getMessage());
            }
            throw new First("first", e);
        }
    }

    private static class First extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public First(String message, Throwable cause) {
            super(message, cause);
        }
        
        public First(String message) {
            super(message);
        }
    }
    
    private static class Second extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public Second(String message, Throwable cause) {
            super(message, cause);
        }
        
        public Second(String message) {
            super(message);
        }
    }

    private static class Third extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public Third(String message) {
            super(message);
        }
    }

    public static void third_() {
        throw new Third("third");
    }

    public static void second_() {
        third_();
    }

    public static void first_() {
        second_();
    }
    
    public static void third__() {
        throw new Third("third");
    }

    public static void second__() {
        try {
            third__();
        } catch (Exception e) {
            throw new Second("second");
        }
    }

    public static void first__() {
        try {
            second__();
        } catch (Exception e) {
            throw new First("first");
        }
    }
    
    public static void main(String[] args) {
        /*
         * This invocation yields:
         * 
         * Exception in thread "main" com.mike.exceptions.ExceptionChaining$First: first
                at com.mike.exceptions.ExceptionChaining.first(ExceptionChaining.java:27)
                at com.mike.exceptions.ExceptionChaining.main(ExceptionChaining.java:111)
            Caused by: com.mike.exceptions.ExceptionChaining$Second: second
                at com.mike.exceptions.ExceptionChaining.second(ExceptionChaining.java:16)
                at com.mike.exceptions.ExceptionChaining.first(ExceptionChaining.java:22)
                ... 1 more
            Caused by: com.mike.exceptions.ExceptionChaining$Third: third
                at com.mike.exceptions.ExceptionChaining.third(ExceptionChaining.java:6)
                at com.mike.exceptions.ExceptionChaining.second(ExceptionChaining.java:11)
                ... 2 more
         */
        first(); //< with exception chaining
        
        /*
         * This invocation yields:
         * 
         * Exception in thread "main" com.mike.exceptions.ExceptionChaining$Third: third
            at com.mike.exceptions.ExceptionChaining.third_(ExceptionChaining.java:50)
            at com.mike.exceptions.ExceptionChaining.second_(ExceptionChaining.java:54)
            at com.mike.exceptions.ExceptionChaining.first_(ExceptionChaining.java:58)
            at com.mike.exceptions.ExceptionChaining.main(ExceptionChaining.java:79)
         */
        //first_(); //< let it bubble up!
        
        /*
         * This invocation yields:
         * 
         * Exception in thread "main" com.mike.exceptions.ExceptionChaining$First: first
            at com.mike.exceptions.ExceptionChaining.first__(ExceptionChaining.java:85)
            at com.mike.exceptions.ExceptionChaining.main(ExceptionChaining.java:118)
         */
        //first__(); //< without exception chaining
    }
}
