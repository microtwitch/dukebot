package de.com.fdm.bot.commands;


public class EchoCommand extends Command {
    public EchoCommand(Parameters params) {
        super(params);
    }

    @Override
    public String execute() {
        String content = getMessage().substring(5);

        if (content.isBlank()) {
            return "No message provided";
        }

        return content;
    }
}