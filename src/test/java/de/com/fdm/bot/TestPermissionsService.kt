package de.com.fdm.bot;


import de.com.fdm.twitch.tmi.TmiMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPermissionsService {

    @Test
    public void testUserNotOwner() {
        TmiMessage msg = new TmiMessage(
                "testUserId",
                "testChannel",
                "testMessage",
                "rawMessage"
        );

        PermissionsService permissionsService = new PermissionsService(
               ","
        );

        boolean result = permissionsService.shouldIgnore(msg);
        assertTrue(result);
    }

    @Test
    public void testMessageStartsWithBotPrefix() {
        TmiMessage msg = new TmiMessage(
                "116672490",
                "testChannel",
                ",testMessage",
                "rawMessage"
        );

        PermissionsService permissionsService = new PermissionsService(
                ","
        );

        boolean result = permissionsService.shouldIgnore(msg);
        assertFalse(result);
    }
}
