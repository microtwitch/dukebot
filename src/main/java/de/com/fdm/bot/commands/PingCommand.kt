package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.twitch.tmi.TmiMessage
import org.springframework.stereotype.Component
import java.lang.management.ManagementFactory
import java.time.Duration

@Component
class PingCommand : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        return String.format("Uptime: %s", formatUptime(calculateUptime()))
    }

    private fun formatUptime(uptime: Long): String {
        return Duration.ofMillis(uptime).toString().substring(2).lowercase()
    }

    private fun calculateUptime() : Long {
        val rb = ManagementFactory.getRuntimeMXBean()
        return rb.uptime
    }
}