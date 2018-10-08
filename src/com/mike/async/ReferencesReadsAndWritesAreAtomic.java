package com.mike.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReferencesReadsAndWritesAreAtomic implements Runnable {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
    private static Map<String, String> configuration = new HashMap<String, String>();

    public void run() {
        for (int i = 0; i < 10000; i++) {
            Map<String, String> currConfig = configuration;
            String value1 = currConfig.get("key-1");
            String value2 = currConfig.get("key-2");
            String value3 = currConfig.get("key-3");
            
            /*String value1 = configuration.get("key-1");
            String value2 = configuration.get("key-2");
            String value3 = configuration.get("key-3");*/
            
            if (!(value1.equals(value2) && value2.equals(value3))) {
                System.out.println("val1:" + currConfig.get("key-1")+"*");
                System.out.println("val2:" + currConfig.get("key-2")+"*");
                System.out.println("val3:" + currConfig.get("key-3")+"*");
                throw new IllegalStateException("Values are not equal.");
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readConfig() {
        Map<String, String> newConfig = new HashMap<String, String>();
        Date now = new Date();
        /*newConfig.put("key-1", sdf.format(now));
        newConfig.put("key-2", sdf.format(now));
        newConfig.put("key-3", sdf.format(now));*/
        configuration.put("key-1", sdf.format(now));
        configuration.put("key-2", sdf.format(now));
        configuration.put("key-3", sdf.format(now));
        //configuration = newConfig;
    }

    public static void main(String[] args) throws InterruptedException {
        readConfig();
        Thread configThread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    readConfig();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "configuration-thread");
        configThread.start();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ReferencesReadsAndWritesAreAtomic(), "thread-" + i);
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        configThread.join();
        System.out.println("[" + Thread.currentThread().getName() + "] All threads have finished.");
    }
}
