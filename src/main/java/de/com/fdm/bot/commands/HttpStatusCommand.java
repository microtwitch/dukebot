package de.com.fdm.bot.commands;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

public class HttpStatusCommand extends Command {
    private final List<String> args;

    public HttpStatusCommand(String channel, List<String> args) {
        super(channel);
        this.args = args;
    }

    @Override
    public String execute() {
        if (this.args.size() == 0) {
            return "No status code provided";
        }

        int statusCode;
        try {
            statusCode = Integer.parseInt(this.args.get(0));
        } catch (NumberFormatException e) {
            return "Not a valid status code";
        }

        String statusCodeText = HttpResponseStatus.valueOf(statusCode).toString();
        if (statusCodeText.contains("Unknown Status")) {
            return "Not a valid status code";
        }

        return String.format("%s https://http.cat/%s", statusCodeText, statusCode);
    }
}
