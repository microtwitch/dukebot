package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.twitch.tmi.TmiMessage
import org.springframework.stereotype.Component

@Component
class RawMsgCommand : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        return tmiMessage.rawMessage
    }
}