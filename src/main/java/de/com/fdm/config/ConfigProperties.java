package de.com.fdm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigProperties {
    @Value("${bot.host}")
    private String botHost;

    @Value("${bot.channels}")
    private String[] botChannels;

    @Value("${grpc.server.port}")
    private int grpcPort;

    @Value("${receiver.host}")
    private String receiverHost;

    @Value("${receiver.port}")
    private int receiverPort;

    public String getBotHost() {
        return botHost;
    }

    public void setBotHost(String botHost) {
        this.botHost = botHost;
    }

    public String[] getBotChannels() {
        return botChannels;
    }

    public void setBotChannels(String[] botChannels) {
        this.botChannels = botChannels;
    }

    public int getGrpcPort() {
        return grpcPort;
    }

    @Bean
    public String getReceiverHost() {
        return receiverHost;
    }

    public void setReceiverHost(String receiverHost) {
        this.receiverHost = receiverHost;
    }

    @Bean
    public int getReceiverPort() {
        return receiverPort;
    }

    public void setReceiverPort(int receiverPort) {
        this.receiverPort = receiverPort;
    }

    public void setGrpcPort(int grpcPort) {
        this.grpcPort = grpcPort;
    }
}
