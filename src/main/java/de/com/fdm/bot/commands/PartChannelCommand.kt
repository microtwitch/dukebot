package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.twitch.tmi.TmiMessage
import de.com.fdm.twitch.tmi.TmiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PartChannelCommand(@param:Autowired private val tmiService: TmiService) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        if (tmiMessage.getArgs().isEmpty()) {
            return "No channel provided"
        }

        val channel = tmiMessage.getArgs()[0]
        tmiService.partChannel(channel)
        return "Parted channel #$channel"
    }
}