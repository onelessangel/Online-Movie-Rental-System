package com.pjsh.onlinemovierental.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NotificationService {
    @Async
    public CompletableFuture<String> sendNotification(String message) {
        int sleepDuration = ThreadLocalRandom.current().nextInt(2_000, 5_001);

        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Notification sent: " + message);
        return CompletableFuture.completedFuture("Time elapsed: " + sleepDuration + "ms");
    }
}
