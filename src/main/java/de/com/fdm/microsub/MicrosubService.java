package de.com.fdm.microsub;

import de.com.fdm.twitch.api.TwitchApiProvider;
import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.microsub.lib.Deletion;
import de.com.fdm.grpc.microsub.lib.Registration;
import de.com.fdm.db.data.MicroSub;
import de.com.fdm.db.repositories.MicroSubRepository;
import de.com.fdm.grpc.microsub.lib.Type;
import de.com.fdm.microsub.client.MicrosubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicrosubService {
    @Autowired
    private TwitchApiProvider twitchApiProvider;

    @Autowired
    private MicroSubRepository microSubRepository;

    @Autowired
    private ConfigProperties config;

    @Autowired
    private MicrosubClient microsubClient;

    public void registerFollowalert(String channel, String target) {

        String id = this.twitchApiProvider.getUserId(target);
        this.microSubRepository.save(new MicroSub(channel, id));

        Registration registration  = Registration.newBuilder()
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .setType(Type.FOLLOW)
                .setId(id)
                .build();

        this.microsubClient.register(registration);
    }

    public void deleteFollowalert(String channel, String target) {
        String broadcasterUserID = this.twitchApiProvider.getUserId(target);

        List<MicroSub> microSubs = this.microSubRepository.findAllByBroadcasterUserId(broadcasterUserID);

        for (MicroSub microSub : microSubs) {
            if (microSub.getChannel().equals(channel)) {
                this.microSubRepository.deleteById(microSub.getId());
            }
        }

        Deletion deletion = Deletion.newBuilder()
                .setId(broadcasterUserID)
                .setType(Type.FOLLOW)
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .build();

        this.microsubClient.delete(deletion);
    }

    public void registerTurtoiseAlerts() {
        Registration registrationFollow = Registration.newBuilder()
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .setType(Type.FOLLOW)
                .setId("80805824")
                .build();

        Registration registrationSub = Registration.newBuilder()
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .setType(Type.SUB)
                .setId("80805824")
                .build();

        microsubClient.register(registrationFollow);
        microsubClient.register(registrationSub);
    }
}
