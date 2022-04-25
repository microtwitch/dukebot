package de.com.fdm.twitch.tmi;

import com.github.twitch4j.chat.events.channel.ChannelMessageActionEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class TmiMessage {
    private final String userId;
    private final String channel;
    private final String message;

    public TmiMessage(String userId, String channel, String message) {
        this.userId = userId;
        this.channel = channel;
        this.message = message;
    }

    public TmiMessage(ChannelMessageEvent event) {
        this.userId = event.getUser().getId();
        this.channel = event.getChannel().getName();
        this.message = event.getMessage();
    }

    public TmiMessage(ChannelMessageActionEvent event) {
        this.userId = event.getUser().getId();
        this.channel = event.getChannel().getName();
        this.message = event.getMessage();
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
}
