package de.com.fdm.bot.commands;


public class EchoCommand extends MessageCommand {
    public EchoCommand(String channel, String message) {
        super(channel, message);
    }

    @Override
    public String execute() {
        String content = getMessage().substring(0, 5);

        if (content.isBlank()) {
            return "No message provided";
        }

        return content;
    }
}