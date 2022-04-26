package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import de.com.fdm.bot.api.WatchtowerService;
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
    public String execute(Parameters params) {
        tmiService.send(params.getChannel(), "Updating...");

        watchtowerService.update();

        return "";
    }
}
