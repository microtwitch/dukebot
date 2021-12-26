package de.com.fdm.bot.commands;


import java.util.List;

public class PingCommand extends Command {
    public PingCommand(String identifier, String channel, String user, List<String> args) {
        super(identifier, channel, user, args);
    }


    public String execute() {
        return "PONG";
    }

    public String getChannel() {
        return super.getChannel();
    }
}
