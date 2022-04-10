package de.com.fdm.bot.commands;


public class UnkownCommand extends Command {
    public UnkownCommand(Parameters params) {
        super(params);
    }

    @Override
    public String execute() {
        return "Unknown command";
    }
}
