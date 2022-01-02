package de.com.fdm.bot.commands;

import de.com.fdm.bot.access.UserLevel;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

public class HttpStatusCommand extends ArgsCommand {
    private final UserLevel level = UserLevel.PLEB;
    public HttpStatusCommand(String channel, List<String> args) {
        super(channel, args);
    }

    @Override
    public String execute() {
        if (this.getArgs().size() == 0) {
            return "No status code provided";
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

    public UserLevel getLevel() {
        return this.level;
    }
}
