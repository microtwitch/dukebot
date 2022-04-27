package de.com.fdm.bot;

import de.com.fdm.bot.api.haste.HasteService;
import de.com.fdm.twitch.tmi.TmiMessage;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
    private final PermissionsService permissionsService;
    private final TmiService tmiService;
    private final CommandRunner commandRunner;
    private final HasteService hasteService;

    public MessageHandler(
            @Autowired PermissionsService permissionsService,
            @Autowired TmiService tmiService,
            @Autowired CommandRunner commandRunner,
            @Autowired HasteService hasteService
    ) {
        this.permissionsService = permissionsService;
        this.tmiService = tmiService;
        this.commandRunner = commandRunner;
        this.hasteService = hasteService;

        tmiService.setCallback(this::handleMessage);
    }

    public void handleMessage(TmiMessage msg) {
        if (permissionsService.shouldIgnore(msg)) {
            return;
        }

        String result = this.commandRunner.runCommand(msg);

        if (msg.getMessage().strip().endsWith("| haste")) {
            String haste = hasteService.upload(result);
            tmiService.send(msg.getChannel(), haste);
            return;
        }

        tmiService.send(msg.getChannel(), result);
    }
}
