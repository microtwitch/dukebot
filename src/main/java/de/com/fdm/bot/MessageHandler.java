package de.com.fdm.bot;

import de.com.fdm.lib.Result;
import de.com.fdm.twitch.tmi.InboundMessage;
import de.com.fdm.twitch.tmi.OutboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private static final String OWNER_ID = "116672490";
    private final String botPrefix;
    private final String botName;

    public MessageHandler(
            @Value("${bot.prefix}") String botPrefix,
            @Value("${bot.name}")  String botName
    ) {
        this.botPrefix = botPrefix;
        this.botName = botName;
    }

    @Autowired
    private CommandRunner commandRunner;

    public Result<OutboundMessage, String> handleMessage(InboundMessage msg) {
        if (msg.getUserName().equals(botName)) {
            return Result.error("Ignored message by bot itself.");
        }

        if (!msg.getText().startsWith(botPrefix)) {
            return Result.error("Wrong prefix.");
        }

        if (!msg.getUserID().equals(OWNER_ID)) {
            return Result.error("User access not allowed.");
        }

        String result = this.commandRunner.runCommand(msg);

        return Result.ok(new OutboundMessage(msg.getChannel(), result));
    }
}
