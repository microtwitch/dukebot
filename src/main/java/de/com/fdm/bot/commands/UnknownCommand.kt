package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage
import org.springframework.stereotype.Component

@Component
class UnknownCommand : Command {
    override fun execute(receiverMessage: ReceiverMessage): String {
        return "Unknown command"
    }
}