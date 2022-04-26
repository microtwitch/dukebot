package de.com.fdm.twitch.tmi

import com.github.twitch4j.chat.events.channel.ChannelMessageActionEvent
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent

data class TmiMessage(
        val userId: String,
        val channel: String,
        val message: String,
        val rawMessage: String) {

    constructor(event: ChannelMessageEvent) : this(
        event.user.id,
        event.channel.name,
        event.message,
        event.messageEvent.rawMessage
    )

    constructor(event: ChannelMessageActionEvent) : this(
        event.user.id,
        event.channel.name,
        event.message,
        event.messageEvent.rawMessage
    )

    fun getArgs() : List<String> {
        val messageText = message.substring(1)
        val chunks = messageText.split(" ")
        return chunks.drop(1)
    }
}