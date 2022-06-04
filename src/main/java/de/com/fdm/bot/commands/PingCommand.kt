package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage
import org.springframework.stereotype.Component
import java.lang.management.ManagementFactory
import java.time.Duration

@Component
class PingCommand : Command {
    override fun execute(receiverMessage: ReceiverMessage): String {
        val uptime = getUptime()
        val memory = getMemoryUsage()
        return String.format("Uptime: %s, Memory: %sMB", uptime, memory)
    }

    private fun getMemoryUsage() : Long {
        val heapUsage = (ManagementFactory.getMemoryMXBean().heapMemoryUsage.used) / (1024 * 1024)
        val nonHeapUsage = (ManagementFactory.getMemoryMXBean().nonHeapMemoryUsage.used) / (1024 * 1024)

        return heapUsage + nonHeapUsage
    }

    private fun getUptime() : String{
        val rb = ManagementFactory.getRuntimeMXBean()
        return formatUptime(rb.uptime)
    }

    private fun formatUptime(uptime: Long) : String {
        return Duration.ofMillis(uptime).toString().substring(2).lowercase()
    }
}