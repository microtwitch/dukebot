package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinChannelCommand implements Command {
    private final TmiService tmiService;

    public JoinChannelCommand(@Autowired TmiService tmiService) {
        this.tmiService = tmiService;
    }
    @Override
    public String execute(TmiMessage tmiMessage) {
        if (tmiMessage.getArgs().size() == 0) {
            return "No channel provided";
        }

        String channel = tmiMessage.getArgs().get(0);
        tmiService.joinChannel(channel);
        return "Joined channel #" + channel;
    }
}
