package de.com.fdm.bot;

import de.com.fdm.bot.commands.CommitCommand;
import de.com.fdm.bot.commands.EchoCommand;
import de.com.fdm.bot.commands.HttpStatusCommand;
import de.com.fdm.bot.commands.IdUserCommand;
import de.com.fdm.bot.commands.JoinChannelCommand;
import de.com.fdm.bot.commands.ListChannelsCommand;
import de.com.fdm.bot.commands.PartChannelCommand;
import de.com.fdm.bot.commands.PingCommand;
import de.com.fdm.bot.commands.RawMsgCommand;
import de.com.fdm.bot.commands.UpdateCommand;
import de.com.fdm.bot.commands.UnkownCommand;
import de.com.fdm.bot.commands.UserIdCommand;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

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
            @Autowired UpdateCommand updateCommand,
            @Autowired RawMsgCommand rawMsgCommand,
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
        commandMap.put("update", updateCommand);
        commandMap.put("rawmsg", rawMsgCommand);

        this.unkownCommand = unkownCommand;
    }

    public String runCommand(TmiMessage msg) {
        String messageText = msg.getMessage().substring(1);
        String[] chunks = messageText.split(" ");

        Command command = commandMap.getOrDefault(chunks[0], unkownCommand);

        return command.execute(msg);
    }
}
