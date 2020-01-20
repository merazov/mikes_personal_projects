package com.mike.spring.retryable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestWithoutSpringRunner {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RemoteCallService service = context.getBean(RemoteCallService.class);
        service.call();
        
        service = new RemoteCallService() {
            @Override
            public String call() throws Exception {
                
                return "Completed";
            }
        };
        service.call();
        
        context.close();
    }
}
