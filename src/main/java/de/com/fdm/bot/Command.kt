package de.com.fdm.bot

import de.com.fdm.bot.api.twitch.tmi.ReceiverMessage

interface Command {
    fun execute(receiverMessage: ReceiverMessage): String
}