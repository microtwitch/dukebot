package de.com.fdm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {
    @Value("${bot.channels}")
    private String[] botChannels;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.auth}")
    private String botAuth;

    @Value("${bot.prefix}")
    private String botPrefix;

    public String[] getBotChannels() {
        return botChannels;
    }

    public void setBotChannels(String[] botChannels) {
        this.botChannels = botChannels;
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

    public String getBotPrefix() {
        return botPrefix;
    }

    public void setBotPrefix(String botPrefix) {
        this.botPrefix = botPrefix;
    }
}
