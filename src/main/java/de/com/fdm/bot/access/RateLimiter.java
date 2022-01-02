package de.com.fdm.bot.access;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class RateLimiter {
    private final Map<String, Instant> userLastMessageTime;

    public RateLimiter() {
        this.userLastMessageTime = new HashMap<>();
    }

    public boolean canSend(String userId) {
        if (userId.equals("116672490")) {
            return true;
        }

        if (!userLastMessageTime.containsKey(userId)) {
            userLastMessageTime.put(userId, Instant.now());
            return true;
        }

        long lastMsgTime = userLastMessageTime.get(userId).getEpochSecond();

        if ((Instant.now().getEpochSecond() - lastMsgTime) >= 10) {
            userLastMessageTime.put(userId, Instant.now());
            return true;
        }

        return false;
    }
}
