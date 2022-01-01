package de.com.fdm.bot.commands;

import de.com.fdm.grpc.receiver.ReceiverService;

import java.util.List;

public class TmpPartCommand extends ArgsCommand {
    private final ReceiverService receiverService;

    public TmpPartCommand(String channel, List<String> args, ReceiverService receiverService) {
        super(channel, args);
        this.receiverService = receiverService;
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No channel provided";
        }

        this.receiverService.partChannel(this.getArgs().get(0));

        return "Parted #" + this.getArgs().get(0);
    }
}
