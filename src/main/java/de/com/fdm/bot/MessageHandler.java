package de.com.fdm.bot;

import de.com.fdm.bot.commands.PermissionsService;
import de.com.fdm.twitch.tmi.TmiMessage;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private final PermissionsService permissionsService;
    private final TmiService tmiService;
    private final CommandRunner commandRunner;

    public MessageHandler(
            @Autowired PermissionsService permissionsService,
            @Autowired TmiService tmiService,
            @Autowired CommandRunner commandRunner
    ) {
        this.permissionsService = permissionsService;
        this.tmiService = tmiService;
        this.commandRunner = commandRunner;

        tmiService.setCallback(this::handleMessage);
    }

    public void handleMessage(TmiMessage msg) {
        if (permissionsService.shouldIgnore(msg)) {
            return;
        }

        String result = this.commandRunner.runCommand(msg);
        tmiService.send(msg.getChannel(), result);
    }
}
