package de.com.fdm.bot.commands;


import de.com.fdm.bot.access.UserLevel;

public abstract class Command {
    private final UserLevel level = UserLevel.OWNER;

    private final String channel;

    public Command(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public UserLevel getLevel() {
        return this.level;
    }

    public abstract String execute();
}
