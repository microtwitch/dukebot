package de.com.fdm.bot;

import de.com.fdm.grpc.receiver.lib.TwitchMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    @Autowired
    private CommandHandler commandHandler;

    public void handleMessage(TwitchMessage msg) {
        if (!msg.getName().equals("matthewde")) {
            return;
        }

        if (!msg.getText().startsWith(",")) {
            return;
        }

        PingCommand cmd = PingCommand.parseMessage(msg);
        this.commandHandler.handleCommand(cmd);
    }
}
