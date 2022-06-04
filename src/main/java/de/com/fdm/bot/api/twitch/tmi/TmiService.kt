package de.com.fdm.bot.api.twitch.tmi

import com.fasterxml.jackson.databind.ObjectMapper
import de.com.fdm.bot.api.haste.HasteService
import org.redisson.Redisson
import org.redisson.api.RTopic
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.util.function.Consumer

@Service
class TmiService(
        @Value("\${bot.auth}") botAuth: String,
        @Value("\${bot.name}") botName: String,
        @param:Autowired private val hasteService: HasteService
) {
    private val client: RedissonClient
    private val mapper = ObjectMapper()
    private val channels = mutableSetOf<String>()
    private val auth: String
    private val name: String
    private lateinit var callback: Consumer<ReceiverMessage>
    private val dispatcherTopic: RTopic

    init {
        val config = Config.fromYAML(File("src/main/resources/redisson_config.yaml"))
        this.client = Redisson.create(config)
        this.dispatcherTopic = client.getTopic("tmiDispatcher")
        this.auth = botAuth
        this.name = botName
    }

    fun setCallback(callback: Consumer<ReceiverMessage>) {
        this.callback = callback
    }

    fun send(channel: String, msg: String) {

        if (msg.length >= 500) {
            val url = hasteService.upload(msg)

            val dispatcherMessage = DispatcherMessage(name, auth, channel, url)
            dispatcherTopic.publish(mapper.writeValueAsString(dispatcherMessage))
        }

        val dispatcherMessage = DispatcherMessage(name, auth, channel, msg)
        dispatcherTopic.publish(mapper.writeValueAsString(dispatcherMessage))
    }

    fun joinChannels(channels: Array<String>) {
        for (channel in channels) {
            joinChannel(channel)
        }
    }

    fun joinChannel(channel: String) {
        val topic = client.getTopic("tmiReceiver")
        topic.publish(channel)

        val listenTopic = client.getTopic("tmiReceiver.$channel")
        listenTopic.addListener(String::class.java) {
                _: CharSequence?, message: String? -> handleMessage(message)
        }

        channels.add(channel)
    }

    fun handleMessage(message: String?) {
        val msg = mapper.readValue(message, ReceiverMessage::class.java)
        callback.accept(msg)
    }

    fun partChannel(channel: String?) {
        val topic = client.getTopic("tmiReceiver.$channel")
        topic.removeAllListeners()
    }

    fun getChannels() : Set<String> {
        return channels
    }
}
