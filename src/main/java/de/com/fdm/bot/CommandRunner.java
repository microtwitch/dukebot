package de.com.fdm.bot;

import de.com.fdm.bot.commands.Command;
import de.com.fdm.bot.commands.EchoCommand;
import de.com.fdm.bot.commands.HttpStatusCommand;
import de.com.fdm.bot.commands.IdUserCommand;
import de.com.fdm.bot.commands.Parameters;
import de.com.fdm.bot.commands.PingCommand;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.bot.commands.UserIdCommand;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.twitch.tmi.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class CommandRunner {
    @Autowired
    private ConfigProperties config;

    private final UnkownCommand unkownCommand;
    private final HashMap<String, Command> commandMap;

    public CommandRunner(
            @Autowired PingCommand pingCommand,
            @Autowired EchoCommand echoCommand,
            @Autowired UserIdCommand userIdCommand,
            @Autowired IdUserCommand idUserCommand,
            @Autowired HttpStatusCommand httpStatusCommand,
            @Autowired UnkownCommand unkownCommand
    ) {
        commandMap = new HashMap<>();
        commandMap.put("ping", pingCommand);
        commandMap.put("echo", echoCommand);
        commandMap.put("httpstatus", httpStatusCommand);
        commandMap.put("id", userIdCommand);
        commandMap.put("user", idUserCommand);

        this.unkownCommand = unkownCommand;
    }

    public String runCommand(InboundMessage msg) {
        String messageText = msg.getText().substring(1);
        String[] chunks = messageText.split(" ");

        List<String> args = Arrays.stream(chunks).toList().subList(1, chunks.length);
        Parameters params = new Parameters(msg.getChannel(), messageText, args);

        Command command = commandMap.getOrDefault(chunks[0], unkownCommand);

        return command.execute(params);
    }
}
