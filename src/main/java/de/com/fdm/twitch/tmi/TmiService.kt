package de.com.fdm.twitch.tmi;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageActionEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.com.fdm.bot.api.haste.HasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Consumer;

@Service
public class TmiService {
    private final TwitchClient client;
    private final HasteService hasteService;
    private Consumer<TmiMessage> callback;

    public TmiService(
            @Value("${bot.auth}") String botAuth,
            @Autowired HasteService hasteService
    ) {
        this.hasteService = hasteService;

        OAuth2Credential credentials = new OAuth2Credential("twitch", botAuth);
        this.client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credentials)
                .build();

    }

    public void setCallback(Consumer<TmiMessage> callback) {
        this.callback = callback;

        client.getEventManager().onEvent(
                ChannelMessageActionEvent.class, this::actionMessageCallback
        );

        client.getEventManager().onEvent(
                ChannelMessageEvent.class, this::messageCallback
        );
    }

    private void messageCallback(ChannelMessageEvent event) {
        this.callback.accept(new TmiMessage(event));
    }

    private void actionMessageCallback(ChannelMessageActionEvent event) {
        this.callback.accept(new TmiMessage(event));
    }

    public void send(String channel, String msg) {
        if (msg.length() >= 500) {
            String url = hasteService.upload(msg);
            client.getChat().sendMessage(channel, url);
            return;
        }

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
