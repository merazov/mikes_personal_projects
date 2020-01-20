package com.mike.spring.retryable;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public class Server implements RemoteCallService{

    @Override
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public String call() throws Exception {
        return null;
    }
}
