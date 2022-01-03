package de.com.fdm.bot.commands;


public class EchoCommand extends MessageCommand {
    public EchoCommand(String channel, String message) {
        super(channel, message);
    }

    @Override
    public String execute() {
        if (getMessage().isBlank()) {
            return "No message provided";
        }

        return getMessage();
    }
}