package com.mike.mdc;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Parent {

    static {
        System.setProperty("logback.configurationFile", "/mike/resources/logback.xml");
    }
    
    private Logger logger = LoggerFactory.getLogger(Parent.class);
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public Parent() {
        // Mimic Web app, save common info in MDC
        MDC.put("IP", "192.168.1.1");
    }

    public void runMultiThreadByExecutor() throws InterruptedException {
        logger.info("Before start child thread");

        executorService.submit(new Child());
        logger.info("After start child thread");

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        logger.info("ExecutorService is over");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        
        System.out.println(new File(".").getCanonicalPath());
        
        Properties p = System.getProperties();
        Enumeration keys = p.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)p.get(key);
            System.out.println("---" + key + ": " + value);
        }
        
        Parent parent = new Parent();
        parent.runMultiThreadByExecutor(); // MDC OK
    }
}
