package de.com.fdm.bot.commands;

import de.com.fdm.bot.twitch.TwitchApiProvider;

import java.util.List;

public class IdUserCommand extends Command {
    private final TwitchApiProvider twitchApiProvider;

    public IdUserCommand(String identifier, String channel, String user, List<String> args, TwitchApiProvider twitchApiProvider) {
        super(identifier, channel, user, args);
        this.twitchApiProvider = twitchApiProvider;
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No id provided";
        }

        String userId = this.getArgs().get(0);
        String userName = this.twitchApiProvider.getUserName(userId);

        return String.format("%s -> %s", userId, userName);
    }
}
