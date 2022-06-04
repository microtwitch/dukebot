package de.com.fdm.main

import de.com.fdm.bot.api.twitch.tmi.TmiReceiverService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication(scanBasePackages = ["de.com.fdm.*"])
class Application(
    @param:Autowired private val tmiReceiverService: TmiReceiverService,
    @param:Value("\${bot.channels}") private val channels: Array<String>
) {
    @EventListener(ApplicationReadyEvent::class)
    fun joinChannels() {
        tmiReceiverService.joinChannels(channels)
    }

    @EventListener(ApplicationReadyEvent::class)
    fun sendRestartMessage() {
        tmiReceiverService.send("matthewde", "Restarted!")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
