package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.twitch.api.TwitchApiProvider;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserIdCommand implements Command {
    private final TwitchApiProvider twitchApiProvider;

    public UserIdCommand(@Autowired TwitchApiProvider twitchApiProvider) {
        this.twitchApiProvider = twitchApiProvider;
    }

    @Override
    public String execute(TmiMessage tmiMessage) {
        if (tmiMessage.getArgs().size() == 0) {
            return "No user id provided";
        }

        String userName = tmiMessage.getArgs().get(0);
        String userId = this.twitchApiProvider.getUserId(userName);

        return String.format("%s -> %s", userName, Objects.requireNonNullElse(userId, "user not found"));
    }
}
