package de.com.fdm.bot;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private final String botPrefix;
    private final String botName;
    private final TmiService tmiService;

    public MessageHandler(
            @Value("${bot.prefix}") String botPrefix,
            @Value("${bot.name}")  String botName,
            @Autowired TmiService tmiService
    ) {
        this.botPrefix = botPrefix;
        this.botName = botName;
        this.tmiService = tmiService;

        tmiService.setCallback(this::handleMessage);
    }

    @Autowired
    private CommandRunner commandRunner;

    public void handleMessage(ChannelMessageEvent msg) {
        if (msg.getUser().getName().equals(botName)) {
            tmiService.send(msg.getChannel().getName(), "Ignored message by bot itself.");
        }

        if (!msg.getMessage().startsWith(botPrefix)) {
            tmiService.send(msg.getChannel().getName(), "Wrong prefix.");
        }

        String result = this.commandRunner.runCommand(msg);

        tmiService.send(msg.getChannel().getName(), result);
    }
}
