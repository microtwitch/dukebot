package de.com.fdm.bot

import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestPermissionsService {
    @Test
    fun testUserNotOwner() {
        val msg = TmiMessage("testChannel", "testMessage", "testUserId", "rawMessage")

        val permissionsService = PermissionsService(",")
        val result = permissionsService.shouldIgnore(msg)
        Assertions.assertTrue(result)
    }

    @Test
    fun testMessageStartsWithBotPrefix() {
        val msg = TmiMessage("testChannel", ",testMessage", "116672490", "rawMessage")

        val permissionsService = PermissionsService(",")
        val result = permissionsService.shouldIgnore(msg)
        Assertions.assertFalse(result)
    }
}