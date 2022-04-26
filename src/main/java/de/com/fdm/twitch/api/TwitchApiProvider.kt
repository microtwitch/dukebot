package de.com.fdm.twitch.api;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.helix.domain.UserList;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwitchApiProvider {
    private final String botAuth;
    private final TwitchClient client;

    public TwitchApiProvider(@Value("${bot.auth}") String botAuth) {
        this.botAuth = botAuth;
        this.client = TwitchClientBuilder
                .builder()
                .withEnableHelix(true)
                .build();
    }

    public String getUserId(String userName) {
        UserList userList;
        try {
            userList = client
                    .getHelix()
                    .getUsers(botAuth, null, List.of(userName))
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
                    .getUsers(botAuth, List.of(userId), null)
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
