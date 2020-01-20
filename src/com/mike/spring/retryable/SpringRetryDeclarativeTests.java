package com.mike.spring.retryable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class SpringRetryDeclarativeTests {

    @Autowired
    private RemoteCallService remoteCallService;

    @Test
    public void testRetry() throws Exception {
        String message = this.remoteCallService.call();
        verify(remoteCallService, times(1000)).call();
        assertEquals(message, "Completed");
    }
}