package de.com.fdm.main

import de.com.fdm.twitch.tmi.TmiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication(scanBasePackages = ["de.com.fdm.*"])
class Application(
        @param:Autowired private val tmiService: TmiService,
        @param:Value("\${bot.channels}") private val channels: Array<String?>
) {
    @EventListener(ApplicationReadyEvent::class)
    fun joinChannels() {
        tmiService.joinChannels(channels)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
