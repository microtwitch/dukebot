package de.com.fdm.bot;

import de.com.fdm.bot.commands.Command;
import de.com.fdm.bot.commands.EchoCommand;
import de.com.fdm.bot.commands.HttpStatusCommand;
import de.com.fdm.bot.commands.IdUserCommand;
import de.com.fdm.bot.commands.PingCommand;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.bot.commands.UserIdCommand;
import de.com.fdm.bot.twitch.TwitchApiProvider;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.microsub.MicrosubService;
import de.com.fdm.tmi.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommandParser {

    @Autowired
    private ConfigProperties config;

    @Autowired
    private  TwitchApiProvider twitchApiProvider;

    @Autowired
    private MicrosubService microsubService;

    public Command parseMessage(InboundMessage msg) {
        String text = msg.getText().substring(1);
        String[] chunks = text.split(" ");
        String identifier = chunks[0];
        List<String> args = Arrays.stream(chunks).toList().subList(1, chunks.length);

        if (identifier.equals("ping")) {
            return new PingCommand(msg.getChannel());
        }

        if (identifier.equals("httpstatus")) {
            return new HttpStatusCommand(msg.getChannel(), args);
        }

        if (identifier.equals("id")) {
            return new UserIdCommand(msg.getChannel(), args, twitchApiProvider);
        }

        if (identifier.equals("user")) {
            return new IdUserCommand(msg.getChannel(), args, twitchApiProvider);
        }

        if (identifier.equals("echo")) {
            return new EchoCommand(msg.getChannel(), msg.getText());
        }

        return new UnkownCommand(msg.getChannel());
    }
}
