package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage
import org.springframework.stereotype.Component

@Component
class RawMsgCommand : Command {
    override fun execute(receiverMessage: ReceiverMessage): String {
        return receiverMessage.rawMsg
    }
}