package de.com.fdm.bot;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private static final String OWNER_ID = "116672490";
    private final String botPrefix;
    private final String botName;
    private final TmiService tmiService;
    private final CommandRunner commandRunner;

    public MessageHandler(
            @Value("${bot.prefix}") String botPrefix,
            @Value("${bot.name}")  String botName,
            @Autowired TmiService tmiService,
            @Autowired CommandRunner commandRunner
    ) {
        this.botPrefix = botPrefix;
        this.botName = botName;
        this.tmiService = tmiService;
        this.commandRunner = commandRunner;

        tmiService.setCallback(this::handleMessage);
    }

    public void handleMessage(ChannelMessageEvent msg) {
        if (!msg.getUser().getId().equals(OWNER_ID)) {
            return;
        }

        if (msg.getUser().getName().equals(botName)) {
            return;
        }

        if (!msg.getMessage().startsWith(botPrefix)) {
            return;
        }

        String result = this.commandRunner.runCommand(msg);
        tmiService.send(msg.getChannel().getName(), result);
    }
}
