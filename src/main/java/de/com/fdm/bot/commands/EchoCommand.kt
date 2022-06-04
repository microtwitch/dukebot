package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage
import org.springframework.stereotype.Component

@Component
class EchoCommand : Command {
    override fun execute(receiverMessage: ReceiverMessage): String {
        if (receiverMessage.message.length < 5) {
            return ""
        }

        return receiverMessage.message.substring(5)
    }
}