package de.com.fdm.twitch.tmi;

public class OutboundMessage {
    private final String channel;
    private final String text;

    public OutboundMessage(String channel, String text) {
        this.channel = channel;
        this.text = text;
    }

    public String getChannel() {
        return channel;
    }

    public String getText() {
        return text;
    }
}
