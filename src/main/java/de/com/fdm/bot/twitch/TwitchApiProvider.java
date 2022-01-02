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
    private final TwitchClient client;

    public TwitchApiProvider() {
        this.client = TwitchClientBuilder
                .builder()
                .withEnableHelix(true)
                .build();
    }

    @Autowired
    private ConfigProperties config;

    public String getUserId(String userName) {
        UserList userList;
        try {
            userList = client
                    .getHelix()
                    .getUsers(this.config.getBotAuth(), null, List.of(userName))
                    .execute();
        } catch (HystrixRuntimeException e) {
            return null;
        }

        List<User> users = userList.getUsers();
        if (users.size() == 0) {
            return null;
        }

        return users.get(0).getId();
    }

    public String getUserName(String userId) {
        UserList userList;
        try {
            userList = client
                    .getHelix()
                    .getUsers(this.config.getBotAuth(), List.of(userId), null)
                    .execute();
        } catch (HystrixRuntimeException e) {
            return "User not found";
        }



        List<User> users = userList.getUsers();
        if (users.size() == 0) {
            return "User not found";
        }

        return users.get(0).getDisplayName();

    }
}
