package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.twitch.tmi.TmiMessage
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class HttpStatusCommand : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        if (tmiMessage.getArgs().isEmpty()) {
            return "No status code provided"
        }

        val code: Int = try {
            tmiMessage.getArgs()[0].toInt()
        } catch (e: NumberFormatException) {
            return "Not a valid status code"
        }

        try {
            val statusCode = HttpStatus.valueOf(code).toString()
            return String.format("%s https://http.cat/%s", statusCode, statusCode)
        } catch (e: IllegalArgumentException) {
            return "Not a valid status code"
        }
    }
}