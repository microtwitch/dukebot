package de.com.fdm.bot;

import de.com.fdm.bot.commands.Command;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.twitch.tmi.InboundMessage;
import de.com.fdm.twitch.tmi.OutboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    @Autowired
    private ConfigProperties config;

    @Autowired
    private CommandParser commandParser;

    // TODO: use result instead of return null
    public OutboundMessage handleMessage(InboundMessage msg) {
        if (msg.getUserName().equals("gopherobot")) {
            return null;
        }

        if (!msg.getText().startsWith(config.getBotPrefix())) {
            return null;
        }

        if (!msg.getUserID().equals("116672490")) {
            return null;
        }

        Command cmd = this.commandParser.parseMessage(msg);

        return new OutboundMessage(cmd.getChannel(), cmd.execute());
    }
}
