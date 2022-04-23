package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import org.springframework.stereotype.Component;

@Component
public class EchoCommand implements Command {
    public String execute(Parameters params) {
        if (params.getMsg().length() < 5) {
            return "";
        }

        return params.getMsg().substring(5);
    }
}