package de.com.fdm.bot.commands;

import de.com.fdm.bot.twitch.TwitchApiProvider;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.microsub.client.MicrosubClient;
import de.com.fdm.grpc.microsub.lib.Registration;
import de.com.fdm.mongo.MicroSub;
import de.com.fdm.mongo.MicroSubRepository;

import java.util.List;

public class AddFollowAlertCommand extends Command {
    private final MicrosubClient microsubClient;
    private final ConfigProperties config;
    private final TwitchApiProvider twitchApiProvider;
    private final MicroSubRepository microSubRepository;

    public AddFollowAlertCommand(String identifier,
                                 String channel,
                                 String user,
                                 List<String> args,
                                 MicrosubClient microsubClient,
                                 ConfigProperties config,
                                 TwitchApiProvider twitchApiProvider,
                                 MicroSubRepository microSubRepository) {

        super(identifier, channel, user, args);

        this.microsubClient = microsubClient;
        this.config = config;
        this.twitchApiProvider = twitchApiProvider;
        this.microSubRepository = microSubRepository;
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No channel provided";
        }

        String id = this.twitchApiProvider.getUserId(this.getArgs().get(0));
        this.microSubRepository.save(new MicroSub(this.getChannel(), id));

        Registration registration  = Registration.newBuilder()
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .setId(id)
                .build();

        this.microsubClient.register(registration);

        return "Success";
    }
}
