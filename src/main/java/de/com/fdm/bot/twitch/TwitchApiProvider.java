package de.com.fdm.bot.twitch;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.helix.domain.UserList;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import de.com.fdm.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwitchApiProvider {

    @Autowired
    private ConfigProperties config;

    public String getUserId(String userName) {
        TwitchClient client = TwitchClientBuilder
                .builder()
                .withEnableHelix(true)
                .build();

        UserList userList;
        try {
            userList = client
                    .getHelix()
                    .getUsers(this.config.getBotAuth(), null, List.of(userName))
                    .execute();
        } catch (HystrixRuntimeException e) {
           return "User not found";
        }

        List<User> users = userList.getUsers();
        if (users.size() == 0) {
            return "Not found";
        }

        return users.get(0).getId();
    }
}
