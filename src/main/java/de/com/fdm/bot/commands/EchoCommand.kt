package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.springframework.stereotype.Component

@Component
class EchoCommand : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        if (tmiMessage.message.length < 5) {
            return ""
        }

        return tmiMessage.message.substring(5)
    }
}