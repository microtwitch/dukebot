package de.com.fdm.bot;

import de.com.fdm.bot.commands.AddFollowAlertCommand;
import de.com.fdm.bot.commands.AddTwitchUserCommand;
import de.com.fdm.bot.commands.Command;
import de.com.fdm.bot.commands.HttpStatusCommand;
import de.com.fdm.bot.commands.IdUserCommand;
import de.com.fdm.bot.commands.PingCommand;
import de.com.fdm.bot.commands.RemoveFollowAlertCommand;
import de.com.fdm.bot.commands.RemoveTwitchUserCommand;
import de.com.fdm.bot.commands.TmpJoinCommand;
import de.com.fdm.bot.commands.TmpPartCommand;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.bot.commands.UserIdCommand;
import de.com.fdm.bot.twitch.TwitchApiProvider;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.db.services.UserService;
import de.com.fdm.grpc.microsub.MicrosubService;
import de.com.fdm.grpc.microsub.client.MicrosubClient;
import de.com.fdm.grpc.receiver.ReceiverService;
import de.com.fdm.grpc.receiver.lib.TwitchMessage;
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
    private ReceiverService receiverService;

    @Autowired
    private MicrosubService microsubService;

    @Autowired
    private UserService userService;

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

        if (identifier.equals("id")) {
            return new UserIdCommand(msg.getChannel(), args, twitchApiProvider);
        }

        if (identifier.equals("user")) {
            return new IdUserCommand(msg.getChannel(), args, twitchApiProvider);
        }

        if (identifier.equals("addfollowalert")) {
            return new AddFollowAlertCommand(msg.getChannel(), args, microsubService);
        }

        if (identifier.equals("removefollowalert")) {
            return new RemoveFollowAlertCommand(msg.getChannel(), args, microsubService);
        }

        if (identifier.equals("tmpjoin")) {
            return new TmpJoinCommand(msg.getChannel(), args, this.receiverService);
        }

        if (identifier.equals("tmppart")) {
            return new TmpPartCommand(msg.getChannel(), args, this.receiverService);
        }

        if (identifier.equals("adduser")) {
            return new AddTwitchUserCommand(msg.getChannel(), args, this.twitchApiProvider, this.userService);
        }

        if (identifier.equals("removeuser")) {
            return new RemoveTwitchUserCommand(msg.getChannel(), args, this.twitchApiProvider, this.userService);
        }

        return new UnkownCommand(msg.getChannel());
    }
}
