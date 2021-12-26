package de.com.fdm.bot.commands;

import java.util.List;

public abstract class Command {
    private final String identifier;
    private final String channel;
    private final String user;
    private final List<String> args;

    public Command(String identifier, String channel, String user, List<String> args) {
        this.identifier = identifier;
        this.channel = channel;
        this.user = user;
        this.args = args;
    }

    public abstract String execute();

    public String getChannel() {
        return this.channel;
    }
}
