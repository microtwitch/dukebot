package de.com.fdm.bot;


import java.util.List;

public class Parameters {
    private final String msg;
    private final List<String> args;

    public Parameters(String msg, List<String> args) {
        this.msg = msg;
        this.args = args;
    }
    public String getMsg() {
        return msg;
    }

    public List<String> getArgs() {
        return args;
    }
}
