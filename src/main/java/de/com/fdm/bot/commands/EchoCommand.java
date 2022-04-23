package de.com.fdm.bot.commands;

import org.springframework.stereotype.Component;

@Component
public class EchoCommand implements Command {
    public String execute(Parameters params) {
        String content = params.getMsg().substring(5);

        if (content.isBlank()) {
            return "No message provided";
        }

        return content;
    }
}