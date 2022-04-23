package de.com.fdm.twitch.tmi;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageActionEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.com.fdm.bot.MessageHandler;
import de.com.fdm.lib.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TmiService {
    private final TwitchClient client;
    private final MessageHandler messageHandler;

    public TmiService(
            @Value("${bot.auth}") String botAuth,
            @Autowired MessageHandler messageHandler
    ) {
        this.messageHandler = messageHandler;

        OAuth2Credential credentials = new OAuth2Credential("twitch", botAuth);
        this.client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credentials)
                .build();

        client.getEventManager().onEvent(ChannelMessageEvent.class, this::handleMessage);
        client.getEventManager().onEvent(ChannelMessageActionEvent.class, this::handleActionMessage);
    }

    private void handleActionMessage(ChannelMessageActionEvent event) {
        ChannelMessageEvent msgEvent = new ChannelMessageEvent(
                event.getChannel(),
                event.getMessageEvent(),
                event.getUser(),
                event.getMessage(),
                event.getPermissions()
        );

        handleMessage(msgEvent);
    }


    private void handleMessage(ChannelMessageEvent event) {
        InboundMessage msg = new InboundMessage(event);

        Result<OutboundMessage, String> result = messageHandler.handleMessage(msg);
        if (result.isOk()) {
            send(result.getValue());
        }
    }

    public void send(OutboundMessage msg) {
        client.getChat().sendMessage(msg.getChannel(), msg.getText());
    }

    public void joinChannels(String[] channels) {
        for (String channel : channels) {
            client.getChat().joinChannel(channel);
        }
    }
}
