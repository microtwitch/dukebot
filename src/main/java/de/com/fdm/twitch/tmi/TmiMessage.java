package de.com.fdm.twitch.tmi;

import com.github.twitch4j.chat.events.channel.ChannelMessageActionEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

import java.util.Arrays;
import java.util.List;

public class TmiMessage {
    private final String userId;
    private final String channel;
    private final String message;
    private final String rawMessage;

    public TmiMessage(String userId, String channel, String message, String rawMessage) {
        this.userId = userId;
        this.channel = channel;
        this.message = message;
        this.rawMessage = rawMessage;
    }

    public TmiMessage(ChannelMessageEvent event) {
        this.userId = event.getUser().getId();
        this.channel = event.getChannel().getName();
        this.message = event.getMessage();
        this.rawMessage = event.getMessageEvent().getRawMessage();
    }

    public TmiMessage(ChannelMessageActionEvent event) {
        this.userId = event.getUser().getId();
        this.channel = event.getChannel().getName();
        this.message = event.getMessage();
        this.rawMessage = event.getMessageEvent().getRawMessage();
    }

    public String getUserId() {
        return userId;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public List<String> getArgs() {
        String messageText = message.substring(1);
        String[] chunks = messageText.split(" ");

        return Arrays.stream(chunks).toList().subList(1, chunks.length);
    }
}
