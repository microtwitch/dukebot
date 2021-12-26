package de.com.fdm.main;


import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.client.ReceiverClient;
import de.com.fdm.grpc.lib.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"de.com.fdm.grpc.service",
        "de.com.fdm.grpc.client",
        "de.com.fdm.config"})
public class Application {

    @Autowired
    private ReceiverClient receiverClient;

    @Autowired
    private ConfigProperties config;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void joinChannels() {
        List<String> channels = new ArrayList<>();

        channels.add("matthewde");
        channels.add("gopherobot");

        Registration registration = Registration.newBuilder()
                .addAllChannels(channels)
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .build();

        receiverClient.register(registration);
    }
}
