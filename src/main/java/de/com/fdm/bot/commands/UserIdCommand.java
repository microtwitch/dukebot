package de.com.fdm.bot.commands;

import de.com.fdm.twitch.api.TwitchApiProvider;

import java.util.Objects;

public class UserIdCommand extends Command {
    private final TwitchApiProvider twitchApiProvider;

    public UserIdCommand(Parameters params) {
        super(params);
        this.twitchApiProvider = params.getApiProvider().getTwitchApiProvider();
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No user id provided";
        }

        String userName = this.getArgs().get(0);
        String userId = this.twitchApiProvider.getUserId(userName);

        return String.format("%s -> %s", userName, Objects.requireNonNullElse(userId, "user not found"));
    }
}
