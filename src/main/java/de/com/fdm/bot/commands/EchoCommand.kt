package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.stereotype.Component;

@Component
public class EchoCommand implements Command {
    public String execute(TmiMessage tmiMessage) {
        if (tmiMessage.getMessage().length() < 5) {
            return "";
        }

        return tmiMessage.getMessage().substring(5);
    }
}