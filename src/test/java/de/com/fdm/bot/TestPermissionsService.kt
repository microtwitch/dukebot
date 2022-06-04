package de.com.fdm.bot

import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestPermissionsService {
    @Test
    fun testUserNotOwner() {
        val msg = TmiMessage(
                "testUserId",
                "testChannel",
                "testMessage",
                "rawMessage"
        )

        val permissionsService = PermissionsService(",")
        val result = permissionsService.shouldIgnore(msg)
        Assertions.assertTrue(result)
    }

    @Test
    fun testMessageStartsWithBotPrefix() {
        val msg = TmiMessage(
                "116672490",
                "testChannel",
                ",testMessage",
                "rawMessage"
        )

        val permissionsService = PermissionsService(",")
        val result = permissionsService.shouldIgnore(msg)
        Assertions.assertFalse(result)
    }
}