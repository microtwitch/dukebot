package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.twitch.api.TwitchApiProvider
import de.com.fdm.twitch.tmi.TmiMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserIdCommand(@param:Autowired private val twitchApiProvider: TwitchApiProvider) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        if (tmiMessage.getArgs().isEmpty()) {
            return "No user id provided"
        }

        val userName = tmiMessage.getArgs()[0]
        val userId = twitchApiProvider.getUserId(userName)
        return String.format("%s -> %s", userName, Objects.requireNonNullElse(userId, "user not found"))
    }
}