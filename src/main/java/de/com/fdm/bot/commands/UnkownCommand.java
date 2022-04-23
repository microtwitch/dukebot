package de.com.fdm.bot.commands;


import org.springframework.stereotype.Component;

@Component
public class UnkownCommand implements Command {
    @Override
    public String execute(Parameters params) {
        return "Unknown command";
    }
}
