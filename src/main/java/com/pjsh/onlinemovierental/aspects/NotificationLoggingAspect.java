package com.pjsh.onlinemovierental.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotificationLoggingAspect {
    @Before("@annotation(org.springframework.scheduling.annotation.Async)")
    public void logBeforeAddMovie() {
        System.out.println("< SENDING NOTIFICATION >");
    }
}
