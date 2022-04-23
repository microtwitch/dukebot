package de.com.fdm.twitch.tmi;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Consumer;

@Service
public class TmiService {
    private final TwitchClient client;

    public TmiService(
            @Value("${bot.auth}") String botAuth
    ) {
        OAuth2Credential credentials = new OAuth2Credential("twitch", botAuth);
        this.client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credentials)
                .build();

    }
    public void setCallback(Consumer<ChannelMessageEvent> callback) {
        client.getEventManager().onEvent(ChannelMessageEvent.class, callback);
    }

    public void send(String channel, String msg) {
        client.getChat().sendMessage(channel, msg);
    }

    public void joinChannels(String[] channels) {
        for (String channel : channels) {
            client.getChat().joinChannel(channel);
        }
    }

    public void joinChannel(String channel) {
        client.getChat().joinChannel(channel);
    }

    public void partChannel(String channel) {
        client.getChat().leaveChannel(channel);
    }

    public Set<String> getChannels() {
        return client.getChat().getChannels();
    }
}
