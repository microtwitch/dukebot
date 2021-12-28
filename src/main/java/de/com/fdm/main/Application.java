package de.com.fdm.main;


import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.receiver.client.ReceiverClient;
import de.com.fdm.grpc.receiver.lib.Registration;
import de.com.fdm.mongo.MicroSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = {"de.com.fdm.grpc.receiver",
        "de.com.fdm.grpc.dispatcher",
        "de.com.fdm.bot",
        "de.com.fdm.config",
        "de.com.fdm.mongo",
        "de.com.fdm.grpc.microsub"})
@EnableMongoRepositories(basePackageClasses = MicroSubRepository.class)
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
        Registration registration = Registration.newBuilder()
                .addAllChannels(Arrays.stream(config.getBotChannels()).toList())
                .setCallback(config.getBotHost() + ":" + config.getGrpcPort())
                .build();

        receiverClient.register(registration);
    }
}
