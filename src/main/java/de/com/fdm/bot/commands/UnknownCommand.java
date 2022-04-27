package de.com.fdm.bot.commands;


import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class UnknownCommand implements Command {
    @Override
    public @NotNull String execute(@NotNull TmiMessage tmiMessage) {
        return "Unknown command";
    }
}
