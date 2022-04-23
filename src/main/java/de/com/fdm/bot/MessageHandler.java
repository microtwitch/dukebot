package de.com.fdm.bot;

import de.com.fdm.config.ConfigProperties;
import de.com.fdm.lib.Result;
import de.com.fdm.twitch.tmi.InboundMessage;
import de.com.fdm.twitch.tmi.OutboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private static final String OWNER_ID = "116672490";

    @Autowired
    private ConfigProperties config;

    @Autowired
    private CommandRunner commandRunner;

    public Result<OutboundMessage, String> handleMessage(InboundMessage msg) {
        if (msg.getUserName().equals(config.getBotName())) {
            return Result.error("Ignored message by bot itself.");
        }

        if (!msg.getText().startsWith(config.getBotPrefix())) {
            return Result.error("Wrong prefix.");
        }

        if (!msg.getUserID().equals(OWNER_ID)) {
            return Result.error("User access not allowed.");
        }

        String result = this.commandRunner.runCommand(msg);

        return Result.ok(new OutboundMessage(msg.getChannel(), result));
    }
}
