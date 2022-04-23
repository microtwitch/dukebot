package de.com.fdm.twitch.tmi;

import com.github.twitch4j.chat.events.channel.ChannelMessageActionEvent;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class InboundMessage {
    private final String userID;
    private final String userName;
    private final String channel;
    private final String text;

    public InboundMessage(ChannelMessageEvent event) {
        this.userID = event.getUser().getId() ;
        this.userName = event.getUser().getName();
        this.channel = event.getChannel().getName();
        this.text = event.getMessage();
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getChannel() {
        return channel;
    }

    public String getText() {
        return text;
    }
}
