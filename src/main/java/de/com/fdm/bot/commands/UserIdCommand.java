package de.com.fdm.bot.commands;

import de.com.fdm.bot.twitch.TwitchApiProvider;

import java.util.List;

public class UserIdCommand extends Command {
    private final TwitchApiProvider twitchApiProvider;

    public UserIdCommand(String identifier, String channel, String user, List<String> args, TwitchApiProvider twitchApiProvider) {
        super(identifier, channel, user, args);
        this.twitchApiProvider = twitchApiProvider;
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No user id provided";
        }

        String userName = this.getArgs().get(0);


        String userId = this.twitchApiProvider.getUserId(userName);

        return String.format("%s -> %s", userName, userId);
    }
}
