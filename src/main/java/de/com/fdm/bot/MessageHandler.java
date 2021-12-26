package de.com.fdm.bot;

import de.com.fdm.grpc.lib.TwitchMessage;

public class MessageHandler {

    public static void handleMessage(TwitchMessage msg) {
        if (!msg.getName().equals("matthewde")) {
            return;
        }

        if (!msg.getText().startsWith(",")) {
            return;
        }

        Command cmd = Command.parseMessage(msg);
        CommandHandler.handleCommand(cmd);
    }
}
