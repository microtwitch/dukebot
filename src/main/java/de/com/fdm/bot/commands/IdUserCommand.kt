package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.twitch.api.TwitchApiProvider
import de.com.fdm.twitch.tmi.TmiMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class IdUserCommand(@param:Autowired private val twitchApiProvider: TwitchApiProvider) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        if (tmiMessage.getArgs().isEmpty()) {
            return "No id provided"
        }

        val userId = tmiMessage.getArgs()[0]
        val userName = twitchApiProvider.getUserName(userId)
        return String.format("%s -> %s", userId, userName)
    }
}