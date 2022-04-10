package de.com.fdm.bot.commands;


import java.util.List;

public abstract class Command {
    private final String channel;
    private final List<String> args;
    private final String message;

    public Command(Parameters params) {
        this.channel = params.getChannel();
        this.args = params.getArgs();
        this.message = params.getMsg();
    }

    public String getChannel() {
        return channel;
    }

    public List<String> getArgs() {
        return this.args;
    }

    public String getMessage() {
        return this.message;
    }

    public abstract String execute();
}
