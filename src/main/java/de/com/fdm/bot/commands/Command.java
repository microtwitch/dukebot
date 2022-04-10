package de.com.fdm.bot.commands;



public abstract class Command {
    private final String channel;

    public Command(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public abstract String execute();
}
