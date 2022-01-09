package de.com.fdm.bot.commands;

import de.com.fdm.bot.twitch.TwitchApiProvider;

public class MockTwitchApiProvider extends TwitchApiProvider {

    public String getUserId(String userName) {
        return "justinfan";
    }
}
