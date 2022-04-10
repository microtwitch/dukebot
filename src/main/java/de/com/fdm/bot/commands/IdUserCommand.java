package de.com.fdm.bot.commands;

import de.com.fdm.twitch.api.TwitchApiProvider;


public class IdUserCommand extends Command {
    private final TwitchApiProvider twitchApiProvider;

    public IdUserCommand(Parameters params) {
        super(params);
        this.twitchApiProvider = params.getApiProvider().getTwitchApiProvider();
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
