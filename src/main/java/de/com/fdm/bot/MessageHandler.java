package de.com.fdm.bot;

import de.com.fdm.bot.access.RateLimiter;
import de.com.fdm.bot.commands.Command;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.db.data.User;
import de.com.fdm.db.services.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private RateLimiter rateLimiter;

    public void handleMessage(TwitchMessage msg) {
        if (msg.getUserName().equals("gopherobot")) {
            return;
        }

        if (!msg.getText().startsWith(config.getBotPrefix())) {
            return;
        }

        Command cmd = this.commandParser.parseMessage(msg);

        User user = userService.getUserForUserId(msg.getUserId());
        if (user == null) {
            return;
        }

        if (user.canExecute(cmd) && rateLimiter.canSend(msg.getUserId())) {
            this.commandHandler.handleCommand(cmd);
        }
    }
}
