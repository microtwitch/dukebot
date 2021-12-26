package de.com.fdm.bot.commands;

import java.util.List;

public class UnkownCommand extends Command {
    public UnkownCommand(String identifier, String channel, String user, List<String> args) {
        super(identifier, channel, user, args);
    }

    @Override
    public String execute() {
        return "Unknown command";
    }
}
