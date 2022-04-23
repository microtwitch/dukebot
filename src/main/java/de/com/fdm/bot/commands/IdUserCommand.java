package de.com.fdm.bot.commands;

import de.com.fdm.twitch.api.TwitchApiProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdUserCommand implements Command {
    private final TwitchApiProvider twitchApiProvider;

    public IdUserCommand(@Autowired TwitchApiProvider twitchApiProvider) {
        this.twitchApiProvider = twitchApiProvider;
    }

    @Override
    public String execute(Parameters params) {
        if (params.getArgs().size() == 0) {
            return "No id provided";
        }

        String userId = params.getArgs().get(0);
        String userName = this.twitchApiProvider.getUserName(userId);

        return String.format("%s -> %s", userId, userName);
    }
}
