package de.com.fdm.bot;


import java.util.List;

public class Parameters {
    private final String channel;
    private final String msg;
    private final List<String> args;

    public Parameters(String channel, String msg, List<String> args) {
        this.channel = channel;
        this.msg = msg;
        this.args = args;
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
}
