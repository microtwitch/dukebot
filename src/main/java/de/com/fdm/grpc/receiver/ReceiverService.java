package de.com.fdm.grpc.receiver;

import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.receiver.client.ReceiverClient;
import de.com.fdm.grpc.receiver.lib.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ReceiverService {
    @Autowired
    private ConfigProperties config;

    @Autowired
    private ReceiverClient receiverClient;

    public void joinChannel(String channel) {
        Registration registration = Registration.newBuilder()
                .addChannels(channel)
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .build();

        receiverClient.register(registration);
    }

    public void joinInitialChannels() {
        Registration registration = Registration.newBuilder()
                .addAllChannels(Arrays.stream(config.getBotChannels()).toList())
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .build();

        receiverClient.register(registration);
    }

    public void partChannel(String channel) {
        Registration registration = Registration.newBuilder()
                .addChannels(channel)
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .build();

        receiverClient.unsubscribe(registration);
    }
}
