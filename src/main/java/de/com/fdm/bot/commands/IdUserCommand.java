package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.twitch.api.TwitchApiProvider;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdUserCommand implements Command {
    private final TwitchApiProvider twitchApiProvider;

    public IdUserCommand(@Autowired TwitchApiProvider twitchApiProvider) {
        this.twitchApiProvider = twitchApiProvider;
    }

    @Override
    public String execute(TmiMessage tmiMessage) {
        if (tmiMessage.getArgs().size() == 0) {
            return "No id provided";
        }

        String userId = tmiMessage.getArgs().get(0);
        String userName = this.twitchApiProvider.getUserName(userId);

        return String.format("%s -> %s", userId, userName);
    }
}
