package de.com.fdm.bot;


import de.com.fdm.grpc.receiver.lib.TwitchMessage;

import java.util.Arrays;
import java.util.List;

public class PingCommand {
    private final String identifier;
    private final String channel;
    private final String user;
    private final List<String> args;

    public PingCommand(String identifier, String channel, String user, List<String> args) {
        this.identifier = identifier;
        this.channel = channel;
        this.user = user;
        this.args = args;
    }

    public static PingCommand parseMessage(TwitchMessage msg) {
        String text = msg.getText().substring(1);
        String[] chunks = text.split(" ");
        String identifier = chunks[0];
        List<String> args = Arrays.stream(chunks).toList().subList(1, chunks.length);

        return new PingCommand(identifier, msg.getChannel(), msg.getName(), args);
    }

    public String execute() {
        return "PONG";
    }

    @Override
    public String toString() {
        return "Command{" +
                "identifier='" + identifier + '\'' +
                ", channel='" + channel + '\'' +
                ", user='" + user + '\'' +
                ", args=" + args +
                '}';
    }

    public String getChannel() {
        return channel;
    }
}
