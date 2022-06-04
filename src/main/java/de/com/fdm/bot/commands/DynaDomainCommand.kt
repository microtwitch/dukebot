package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.dynadomain.DynaDomainService
import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DynaDomainCommand(@param:Autowired private val dynaDomainService: DynaDomainService) : Command {
    override fun execute(receiverMessage: ReceiverMessage): String {
        if (receiverMessage.getArgs().size < 2) {
            return "Not enough arguments"
        }

        val domain = receiverMessage.getArgs()[0]
        val target = receiverMessage.getArgs()[1]

        dynaDomainService.setRedirect(domain, target)

        return "$domain.feelsdankman.xyz"
    }
}