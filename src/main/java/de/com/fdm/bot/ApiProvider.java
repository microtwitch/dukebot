package de.com.fdm.bot;

import de.com.fdm.twitch.api.TwitchApiProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiProvider {
    @Autowired
    private TwitchApiProvider twitchApiProvider;

    public TwitchApiProvider getTwitchApiProvider() {
        return twitchApiProvider;
    }
}
