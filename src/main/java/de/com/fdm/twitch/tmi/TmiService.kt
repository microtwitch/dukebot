package de.com.fdm.twitch.tmi

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential
import com.github.twitch4j.TwitchClient
import com.github.twitch4j.TwitchClientBuilder
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent
import de.com.fdm.bot.api.haste.HasteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class TmiService(
        @Value("\${bot.auth}") botAuth: String,
        @param:Autowired private val hasteService: HasteService
) {
    private val client: TwitchClient

    init {
        val credentials = OAuth2Credential("twitch", botAuth)
        client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credentials)
                .build()
    }

    fun setCallback(callback: Consumer<TmiMessage>) {
        client.eventManager.onEvent(ChannelMessageEvent::class.java) {
            event: ChannelMessageEvent -> callback.accept(TmiMessage(event))
        }
    }

    fun send(channel: String?, msg: String) {
        if (msg.length >= 500) {
            val url = hasteService.upload(msg)
            client.chat.sendMessage(channel, url)
            return
        }

        client.chat.sendMessage(channel, msg)
    }

    fun joinChannels(channels: Array<String?>) {
        for (channel in channels) {
            client.chat.joinChannel(channel)
        }
    }

    fun joinChannel(channel: String?) {
        client.chat.joinChannel(channel)
    }

    fun partChannel(channel: String?) {
        client.chat.leaveChannel(channel)
    }

    fun getChannels() : Set<String> {
        return client.chat.channels
    }
}