package de.com.fdm.bot.commands;

public abstract class MessageCommand extends Command {
    private final String message;
    public MessageCommand(String channel, String message) {
        super(channel);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
