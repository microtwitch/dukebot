package de.com.fdm.bot.commands;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

public class HttpStatusCommand extends Command {
    public HttpStatusCommand(String identifier, String channel, String user, List<String> args) {
        super(identifier, channel, user, args);
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No status codep rovided";
        }

        int statusCode;
        try {
            statusCode = Integer.parseInt(this.getArgs().get(0));
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
