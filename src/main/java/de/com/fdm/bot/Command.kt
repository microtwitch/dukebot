package de.com.fdm.bot

import de.com.fdm.bot.api.twitch.tmi.TmiMessage

interface Command {
    fun execute(tmiMessage: TmiMessage): String
}