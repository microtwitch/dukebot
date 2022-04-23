package de.com.fdm.bot;

import de.com.fdm.bot.commands.Command;
import de.com.fdm.bot.commands.Identifier;
import de.com.fdm.bot.commands.Parameters;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.lib.Result;
import de.com.fdm.twitch.api.TwitchApiProvider;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.twitch.tmi.InboundMessage;
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
    private ApiProvider apiProvider;

    public Command parseMessage(InboundMessage msg) {
        String messageText = msg.getText().substring(1);
        String[] chunks = messageText.split(" ");

        List<String> args = Arrays.stream(chunks).toList().subList(1, chunks.length);
        Parameters params = new Parameters(msg.getChannel(), messageText, args, apiProvider);

        Result<Identifier, Exception> result = Identifier.getIdentifier(chunks[0]);
        if (result.isError()) {
            return new UnkownCommand(params);
        }

        return result.getValue().getCommand(params);
    }
}
