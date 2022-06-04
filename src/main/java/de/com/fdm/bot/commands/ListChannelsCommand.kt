package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import de.com.fdm.bot.api.twitch.tmi.TmiReceiverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ListChannelsCommand(@param:Autowired private val tmiReceiverService: TmiReceiverService) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        return tmiReceiverService.getChannels().toString()
    }
}