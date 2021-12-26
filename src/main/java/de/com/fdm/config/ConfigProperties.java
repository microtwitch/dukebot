package de.com.fdm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {
    @Value("${bot.host}")
    private String botHost;

    @Value("${bot.channels}")
    private String[] botChannels;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.auth}")
    private String botAuth;

    @Value("${grpc.server.port}")
    private int grpcPort;

    @Value("${receiver.host}")
    private String receiverHost;

    @Value("${receiver.port}")
    private int receiverPort;

    @Value("${dispatcher.host}")
    private String dispatcherHost;

    @Value("${dispatcher.port}")
    private int dispatcherPort;

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

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotAuth() {
        return botAuth;
    }

    public void setBotAuth(String botAuth) {
        this.botAuth = botAuth;
    }

    @Bean("receiverHost")
    public String getReceiverHost() {
        return receiverHost;
    }

    public void setReceiverHost(String receiverHost) {
        this.receiverHost = receiverHost;
    }

    @Bean("receiverPort")
    public int getReceiverPort() {
        return receiverPort;
    }

    public void setReceiverPort(int receiverPort) {
        this.receiverPort = receiverPort;
    }

    public void setGrpcPort(int grpcPort) {
        this.grpcPort = grpcPort;
    }

    @Bean("dispatcherHost")
    public String getDispatcherHost() {
        return dispatcherHost;
    }

    public void setDispatcherHost(String dispatcherHost) {
        this.dispatcherHost = dispatcherHost;
    }

    @Bean("dispatcherPort")
    public int getDispatcherPort() {
        return dispatcherPort;
    }

    public void setDispatcherPort(int dispatcherPort) {
        this.dispatcherPort = dispatcherPort;
    }
}
