package de.com.fdm.bot;

import de.com.fdm.bot.commands.AddFollowAlertCommand;
import de.com.fdm.bot.commands.Command;
import de.com.fdm.bot.commands.HttpStatusCommand;
import de.com.fdm.bot.commands.IdUserCommand;
import de.com.fdm.bot.commands.PingCommand;
import de.com.fdm.bot.commands.RemoveFollowAlertCommand;
import de.com.fdm.bot.commands.TmpJoinCommand;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.bot.commands.UserIdCommand;
import de.com.fdm.bot.twitch.TwitchApiProvider;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.microsub.client.MicrosubClient;
import de.com.fdm.grpc.receiver.ReceiverService;
import de.com.fdm.grpc.receiver.lib.TwitchMessage;
import de.com.fdm.mongo.MicroSubRepository;
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
    private MicrosubClient microsubClient;

    @Autowired
    private MicroSubRepository microSubRepository;

    @Autowired
    private ReceiverService receiverService;


    public Command parseMessage(TwitchMessage msg) {
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

        if (identifier.equals("user")) {
            return new UserIdCommand(msg.getChannel(), args, this.twitchApiProvider);
        }

        if (identifier.equals("id")) {
            return new IdUserCommand(msg.getChannel(), args, this.twitchApiProvider);
        }

        if (identifier.equals("addfollowalert")) {
            return new AddFollowAlertCommand(
                    msg.getChannel(),
                    args,
                    this.microsubClient,
                    this.config,
                    this.twitchApiProvider,
                    this.microSubRepository);
        }

        if (identifier.equals("removefollowalert")) {
            return new RemoveFollowAlertCommand(
                    msg.getChannel(),
                    args,
                    this.microsubClient,
                    this.config,
                    this.twitchApiProvider,
                    this.microSubRepository);
        }

        if (identifier.equals("tmpjoin")) {
            return new TmpJoinCommand(msg.getChannel(), args, this.receiverService);
        }

        return new UnkownCommand(msg.getChannel());
    }
}
