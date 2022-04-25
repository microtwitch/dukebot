package de.com.fdm.bot.commands;

import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PermissionsService {
    private static final String OWNER_ID = "116672490";
    private final String botPrefix;

    public PermissionsService(
            @Value("${bot.prefix}") String botPrefix
    ) {
        this.botPrefix = botPrefix;
    }

    public boolean shouldIgnore(TmiMessage msg) {
        if (!msg.getUserId().equals(OWNER_ID)) {
            return true;
        }

        return !msg.getMessage().startsWith(botPrefix);
    }
}
