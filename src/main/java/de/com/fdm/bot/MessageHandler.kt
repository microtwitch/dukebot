package de.com.fdm.bot

import de.com.fdm.bot.api.haste.HasteService
import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage
import de.com.fdm.bot.api.twitch.tmi.TmiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MessageHandler(
    @param:Autowired private val permissionsService: PermissionsService,
    @param:Autowired private val tmiService: TmiService,
    @param:Autowired private val commandRunner: CommandRunner,
    @param:Autowired private val hasteService: HasteService
) {
    init {
        tmiService.setCallback { msg: ReceiverMessage -> handleMessage(msg) }
    }

    private fun handleMessage(msg: ReceiverMessage) {
        if (permissionsService.shouldIgnore(msg)) {
            return
        }

        val result = commandRunner.runCommand(msg)
        if (msg.message.endsWith("| haste")) {
            val haste = hasteService.upload(result)
            tmiService.send(msg.channel, haste)
            return
        }

        tmiService.send(msg.channel, result)
    }
}