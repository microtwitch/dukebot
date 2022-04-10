package de.com.fdm.bot.commands;

import de.com.fdm.twitch.api.TwitchApiProvider;

public class MockTwitchApiProvider extends TwitchApiProvider {

    public String getUserId(String userName) {
        return "justinfan";
    }
}
