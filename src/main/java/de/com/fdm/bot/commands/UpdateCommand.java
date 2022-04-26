package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.api.watchtower.WatchtowerService;
import de.com.fdm.twitch.tmi.TmiMessage;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCommand implements Command {
    private final WatchtowerService watchtowerService;
    private final TmiService tmiService;

    public UpdateCommand(
            @Autowired WatchtowerService watchtowerService,
            @Autowired TmiService tmiService
    ) {
        this.watchtowerService = watchtowerService;
        this.tmiService = tmiService;
    }

    @Override
    public String execute(TmiMessage tmiMessage) {
        tmiService.send(tmiMessage.getChannel(), "Updating...");

        watchtowerService.update();

        return "";
    }
}
