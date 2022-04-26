package de.com.fdm.bot.commands;


import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.stereotype.Component;

@Component
public class UnkownCommand implements Command {
    @Override
    public String execute(TmiMessage tmiMessage) {
        return "Unknown command";
    }
}
