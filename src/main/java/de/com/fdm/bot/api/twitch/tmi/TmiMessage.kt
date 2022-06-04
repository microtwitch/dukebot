package de.com.fdm.bot.api.twitch.tmi

import com.fasterxml.jackson.annotation.JsonProperty


data class TmiMessage(
        @JsonProperty("channel")
        val channel: String,
        @JsonProperty("message")
        val message: String,
        @JsonProperty("userId")
        val userId: String,
        @JsonProperty("rawMsg")
        val rawMsg: String
        ) {

    fun getArgs() : List<String> {
        val messageText = message.substring(1)
        val chunks = messageText.split(" ")
        return chunks.drop(1)
    }
}