package de.com.fdm.bot.access;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RateLimiterTest {

    @Test
    void testOwnerSending() {
        RateLimiter rateLimiter = new RateLimiter();

        boolean canSend = rateLimiter.canSend("116672490");
        assertTrue(canSend);
    }

    @Test
    void testSendingFirstTime() {
        RateLimiter rateLimiter = new RateLimiter();

        boolean canSend = rateLimiter.canSend("12345");
        assertTrue(canSend);
    }

    @Test
    void testHittingLimit() {
        RateLimiter rateLimiter = new RateLimiter();

        boolean canSend = rateLimiter.canSend("12345");
        assertTrue(canSend);
        boolean canSend2 = rateLimiter.canSend("12345");
        assertFalse(canSend2);
    }

    @Test
    void testNotHittingLimit() throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter();

        boolean canSend = rateLimiter.canSend("12345");
        assertTrue(canSend);
        Thread.sleep(10000);
        boolean canSend2 = rateLimiter.canSend("12345");
        assertTrue(canSend2);

    }
}
