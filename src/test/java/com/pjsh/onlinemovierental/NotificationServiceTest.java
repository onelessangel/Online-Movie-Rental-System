package com.pjsh.onlinemovierental;

import com.pjsh.onlinemovierental.services.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {
    @InjectMocks
    private NotificationService notificationService;

    @Test
    public void testSendNotification() throws Exception {
        CompletableFuture<String> result = notificationService.sendNotification("Test message");
        System.out.println(result.get());
        assertNotNull(result);
    }
}
