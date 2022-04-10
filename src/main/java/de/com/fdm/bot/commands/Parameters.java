package de.com.fdm.bot.commands;

import de.com.fdm.bot.ApiProvider;

import java.util.List;

public class Parameters {
    private final String channel;
    private final String msg;
    private final List<String> args;
    private final ApiProvider apiProvider;

    public Parameters(String channel, String msg, List<String> args, ApiProvider apiProvider) {
        this.channel = channel;
        this.msg = msg;
        this.args = args;
        this.apiProvider = apiProvider;
    }

    public String getChannel() {
        return channel;
    }

    public String getMsg() {
        return msg;
    }

    public List<String> getArgs() {
        return args;
    }

    public ApiProvider getApiProvider() {
        return apiProvider;
    }
}
