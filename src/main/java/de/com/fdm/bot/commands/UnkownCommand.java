package de.com.fdm.bot.commands;


public class UnkownCommand extends Command {
    public UnkownCommand(String channel) {
        super(channel);
    }

    @Override
    public String execute() {
        return "Unknown command";
    }
}
