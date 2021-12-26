package de.com.fdm.bot;

import de.com.fdm.bot.commands.Command;
import de.com.fdm.bot.commands.HttpStatusCommand;
import de.com.fdm.bot.commands.PingCommand;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.grpc.receiver.lib.TwitchMessage;

import java.util.Arrays;
import java.util.List;

public class CommandParser {
    public static Command parseMessage(TwitchMessage msg) {
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

        return new UnkownCommand(identifier, msg.getChannel(), msg.getName(), args);
    }
}
