package com.mike.mdc;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Child implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Child.class);

    // contextMap is set when new Child() is called
    private Map<String, String> contextMap = MDC.getCopyOfContextMap();

    public void run() {
        MDC.setContextMap(contextMap); // set contextMap when thread start
        logger.info("Running in the child thread");
    }
}
