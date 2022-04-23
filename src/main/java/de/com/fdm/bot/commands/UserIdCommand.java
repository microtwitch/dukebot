package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import de.com.fdm.twitch.api.TwitchApiProvider;
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
    public String execute(Parameters params) {
        if (params.getArgs().size() == 0) {
            return "No user id provided";
        }

        String userName = params.getArgs().get(0);
        String userId = this.twitchApiProvider.getUserId(userName);

        return String.format("%s -> %s", userName, Objects.requireNonNullElse(userId, "user not found"));
    }
}
