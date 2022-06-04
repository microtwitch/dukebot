package de.com.fdm.bot

import de.com.fdm.bot.api.haste.HasteService
import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import de.com.fdm.bot.api.twitch.tmi.TmiReceiverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MessageHandler(
    @param:Autowired private val permissionsService: PermissionsService,
    @param:Autowired private val tmiReceiverService: TmiReceiverService,
    @param:Autowired private val commandRunner: CommandRunner,
    @param:Autowired private val hasteService: HasteService
) {
    init {
        tmiReceiverService.setCallback { msg: TmiMessage -> handleMessage(msg) }
    }

    private fun handleMessage(msg: TmiMessage) {
        if (permissionsService.shouldIgnore(msg)) {
            return
        }

        val result = commandRunner.runCommand(msg)
        if (msg.message.endsWith("| haste")) {
            val haste = hasteService.upload(result)
            tmiReceiverService.send(msg.channel, haste)
            return
        }

        tmiReceiverService.send(msg.channel, result)
    }
}