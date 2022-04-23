package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartChannelCommand implements Command {
    private final TmiService tmiService;

    public PartChannelCommand(@Autowired TmiService tmiService) {
        this.tmiService = tmiService;
    }
    @Override
    public String execute(Parameters params) {
        if (params.getArgs().size() == 0) {
            return "No channel provided";
        }

        String channel = params.getArgs().get(0);
        tmiService.partChannel(channel);
        return "Parted channel #" + channel;
    }
}
