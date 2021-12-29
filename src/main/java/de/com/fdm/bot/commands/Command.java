package de.com.fdm.bot.commands;


public abstract class Command {
    private String channel;

    public Command(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public abstract String execute();
}
