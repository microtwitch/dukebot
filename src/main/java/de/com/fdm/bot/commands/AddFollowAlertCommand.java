package de.com.fdm.bot.commands;

import de.com.fdm.grpc.microsub.MicrosubService;

import java.util.List;

public class AddFollowAlertCommand extends ArgsCommand {
    private final MicrosubService microsubService;

    public AddFollowAlertCommand(String channel, List<String> args, MicrosubService microsubService) {
        super(channel, args);
        this.microsubService = microsubService;
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No channel provided";
        }

        microsubService.registerFollowalert(this.getChannel(), this.getArgs().get(0));
        return "Success";
    }
}
