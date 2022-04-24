package de.com.fdm.bot;

import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.com.fdm.bot.commands.CommitCommand;
import de.com.fdm.bot.commands.EchoCommand;
import de.com.fdm.bot.commands.HttpStatusCommand;
import de.com.fdm.bot.commands.IdUserCommand;
import de.com.fdm.bot.commands.JoinChannelCommand;
import de.com.fdm.bot.commands.ListChannelsCommand;
import de.com.fdm.bot.commands.PartChannelCommand;
import de.com.fdm.bot.commands.PingCommand;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.bot.commands.UserIdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class CommandRunner {
    private final UnkownCommand unkownCommand;
    private final HashMap<String, Command> commandMap;

    public CommandRunner(
            @Autowired PingCommand pingCommand,
            @Autowired EchoCommand echoCommand,
            @Autowired UserIdCommand userIdCommand,
            @Autowired IdUserCommand idUserCommand,
            @Autowired HttpStatusCommand httpStatusCommand,
            @Autowired ListChannelsCommand listChannelsCommand,
            @Autowired JoinChannelCommand joinChannelCommand,
            @Autowired PartChannelCommand partChannelCommand,
            @Autowired CommitCommand commitCommand,
            @Autowired UnkownCommand unkownCommand
    ) {
        commandMap = new HashMap<>();
        commandMap.put("ping", pingCommand);
        commandMap.put("echo", echoCommand);
        commandMap.put("httpstatus", httpStatusCommand);
        commandMap.put("id", userIdCommand);
        commandMap.put("user", idUserCommand);
        commandMap.put("channels", listChannelsCommand);
        commandMap.put("joinchannel", joinChannelCommand);
        commandMap.put("partchannel", partChannelCommand);
        commandMap.put("commit", commitCommand);

        this.unkownCommand = unkownCommand;
    }

    public String runCommand(ChannelMessageEvent msg) {
        String messageText = msg.getMessage().substring(1);
        String[] chunks = messageText.split(" ");

        List<String> args = Arrays.stream(chunks).toList().subList(1, chunks.length);
        Parameters params = new Parameters(msg.getChannel().getName(), messageText, args);

        Command command = commandMap.getOrDefault(chunks[0], unkownCommand);

        return command.execute(params);
    }
}
