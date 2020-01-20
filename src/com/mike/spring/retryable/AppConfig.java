package com.mike.spring.retryable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@Configuration
public class AppConfig {

    private static int counter = 0;
    
    @Bean
    public RemoteCallService remoteCallService() throws Exception {
        /*RemoteCallService remoteService = mock(RemoteCallService.class);
        when(remoteService.call())
                .thenThrow(new RuntimeException("Remote Exception 1"))
                .thenThrow(new RuntimeException("Remote Exception 2"))
                .thenReturn("Completed");

        return remoteService;*/

        return new RemoteCallService() {
            @Override
            public String call() throws Exception {
                if (counter == 0) {
                    counter++;
                    throw new RuntimeException("Remote Exception 1");
                }

                return "Completed";
            }
        };
    }
}