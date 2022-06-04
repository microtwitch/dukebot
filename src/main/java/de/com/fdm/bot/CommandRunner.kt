package de.com.fdm.bot

import de.com.fdm.bot.commands.*
import de.com.fdm.bot.commands.formula1.ConstructorStandingsCommand
import de.com.fdm.bot.commands.formula1.DriverStandingsCommand
import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommandRunner(
    @Autowired pingCommand: PingCommand,
    @Autowired echoCommand: EchoCommand,
    @Autowired userIdCommand: UserIdCommand,
    @Autowired idUserCommand: IdUserCommand,
    @Autowired httpStatusCommand: HttpStatusCommand,
    @Autowired listChannelsCommand: ListChannelsCommand,
    @Autowired joinChannelCommand: JoinChannelCommand,
    @Autowired partChannelCommand: PartChannelCommand,
    @Autowired commitCommand: CommitCommand,
    @Autowired updateCommand: UpdateCommand,
    @Autowired rawMsgCommand: RawMsgCommand,
    @Autowired driverStandingsCommand: DriverStandingsCommand,
    @Autowired constructorStandingsCommand: ConstructorStandingsCommand,
    @Autowired domainCommand: DynaDomainCommand,
    @Autowired unknownCommand: UnknownCommand
) {
    private val unknownCommand: UnknownCommand
    private val commandMap: HashMap<String, Command> = HashMap()

    init {
        commandMap["ping"] = pingCommand
        commandMap["echo"] = echoCommand
        commandMap["httpstatus"] = httpStatusCommand
        commandMap["id"] = userIdCommand
        commandMap["user"] = idUserCommand
        commandMap["channels"] = listChannelsCommand
        commandMap["joinchannel"] = joinChannelCommand
        commandMap["partchannel"] = partChannelCommand
        commandMap["commit"] = commitCommand
        commandMap["update"] = updateCommand
        commandMap["rawmsg"] = rawMsgCommand
        commandMap["driverstandings"] = driverStandingsCommand
        commandMap["constructorstandings"] = constructorStandingsCommand
        commandMap["domain"] = domainCommand

        this.unknownCommand = unknownCommand
    }

    fun runCommand(msg: TmiMessage): String {
        val messageText = msg.message.substring(1)
        val chunks = messageText.split(" ")
        val command = commandMap.getOrDefault(chunks[0], unknownCommand)
        return command.execute(msg)
    }
}