package de.com.fdm.bot;

import de.com.fdm.bot.commands.Command;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.receiver.lib.TwitchMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    @Autowired
    private ConfigProperties config;

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private CommandParser commandParser;

    public void handleMessage(TwitchMessage msg) {
        if (!msg.getName().equals("matthewde")) {
            return;
        }

        if (!msg.getText().startsWith(config.getBotPrefix())) {
            return;
        }

        Command cmd = this.commandParser.parseMessage(msg);
        this.commandHandler.handleCommand(cmd);
    }
}
