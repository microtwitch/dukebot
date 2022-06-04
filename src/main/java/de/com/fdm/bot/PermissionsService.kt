package de.com.fdm.bot

import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class PermissionsService(
        @param:Value("\${bot.prefix}") private val botPrefix: String
) {
    private val ownerId = "116672490"

    fun shouldIgnore(msg: TmiMessage): Boolean {
        if (msg.userId != ownerId) {
            return true
        }

        if (!msg.message.startsWith(botPrefix)) {
            return true
        }

        return false
    }
}