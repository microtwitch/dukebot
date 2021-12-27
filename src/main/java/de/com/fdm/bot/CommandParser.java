package de.com.fdm.bot;

import de.com.fdm.bot.commands.*;
import de.com.fdm.bot.twitch.TwitchApiProvider;
import de.com.fdm.grpc.receiver.lib.TwitchMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommandParser {

    @Autowired
    private  TwitchApiProvider twitchApiProvider;


    public Command parseMessage(TwitchMessage msg) {
        String text = msg.getText().substring(1);
        String[] chunks = text.split(" ");
        String identifier = chunks[0];
        List<String> args = Arrays.stream(chunks).toList().subList(1, chunks.length);

        if (identifier.equals("ping")) {
            return new PingCommand(identifier, msg.getChannel(), msg.getName(), args);
        }
        if (identifier.equals("httpstatus")) {
            return new HttpStatusCommand(identifier, msg.getChannel(), msg.getName(), args);
        }
        if (identifier.equals("id")) {
            return new UserIdCommand(identifier, msg.getChannel(), msg.getName(), args, twitchApiProvider);
        }

        if (identifier.equals("user")) {
            return new IdUserCommand(identifier, msg.getChannel(), msg.getName(), args, twitchApiProvider);
        }

        return new UnkownCommand(identifier, msg.getChannel(), msg.getName(), args);
    }
}
