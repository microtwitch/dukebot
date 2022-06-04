package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage
import de.com.fdm.bot.api.twitch.tmi.TmiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ListChannelsCommand(@param:Autowired private val tmiService: TmiService) : Command {
    override fun execute(receiverMessage: ReceiverMessage): String {
        return tmiService.getChannels().toString()
    }
}