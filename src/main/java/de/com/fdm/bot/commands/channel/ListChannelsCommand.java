package de.com.fdm.bot.commands.channel;

import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListChannelsCommand implements Command {
    private final TmiService tmiService;

    public ListChannelsCommand(@Autowired TmiService tmiService) {
        this.tmiService = tmiService;
    }

    @Override
    public String execute(TmiMessage tmiMessage) {
        return tmiService.getChannels().toString();
    }
}
