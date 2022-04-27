package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
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
    public String execute(TmiMessage tmiMessage) {
        if (tmiMessage.getArgs().size() == 0) {
            return "No channel provided";
        }

        String channel = tmiMessage.getArgs().get(0);
        tmiService.partChannel(channel);
        return "Parted channel #" + channel;
    }
}
