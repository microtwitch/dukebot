package de.com.fdm.bot.commands;

import de.com.fdm.bot.access.UserLevel;
import de.com.fdm.bot.twitch.TwitchApiProvider;
import de.com.fdm.db.services.UserService;

import java.util.List;

public class AddTwitchUserCommand extends ArgsCommand {
    private final TwitchApiProvider twitchApiProvider;
    private final UserService userService;

    public AddTwitchUserCommand(String channel,
                                List<String> args,
                                TwitchApiProvider twitchApiProvider,
                                UserService userService) {
        super(channel, args);
        this.twitchApiProvider = twitchApiProvider;
        this.userService = userService;
    }

    @Override
    public String execute() {
        if (getArgs().size() == 0) {
            return "No valid user provided";
        }

        String userName = getArgs().get(0);
        if (userName.isBlank()) {
            return "No valid user provided";
        }

        String userId = twitchApiProvider.getUserId(userName);
        if (userId == null) {
            return "No valid user provided";
        }

        if (getArgs().size() == 1) {
            return "No valid level provided";
        }

        UserLevel level;
        try {
            level = UserLevel.valueOf(getArgs().get(1));
        } catch (IllegalArgumentException e) {
            return "No valid level provided";
        }

        this.userService.save(userId, level);
        return "Success";
    }
}
