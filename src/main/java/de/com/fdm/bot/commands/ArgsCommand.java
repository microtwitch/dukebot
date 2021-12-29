package de.com.fdm.bot.commands;

import java.util.List;

public abstract class ArgsCommand extends Command {
    private final List<String> args;

    public ArgsCommand(String channel, List<String> args) {
        super(channel);
        this.args = args;
    }

    public List<String> getArgs() {
        return args;
    }
}
