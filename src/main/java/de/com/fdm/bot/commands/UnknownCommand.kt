package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.springframework.stereotype.Component

@Component
class UnknownCommand : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        return "Unknown command"
    }
}