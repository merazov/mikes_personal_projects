package com.mike.junit.rules;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Rule;
import org.junit.Test;

public class MyLoggerTest {

    @Rule
    public final TestLogger logger = new TestLogger();

    @Test
    public void checkOutMyLogger() {
        final Logger log = logger.getLogger();
        log.log(Level.INFO, "Your test is showing!");
    }
}