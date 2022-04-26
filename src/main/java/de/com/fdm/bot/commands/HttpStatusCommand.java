package de.com.fdm.bot.commands;


import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class HttpStatusCommand implements Command {
    @Override
    public String execute(TmiMessage tmiMessage) {
        if (tmiMessage.getArgs().size() == 0) {
            return "No status code provided";
        }

        int statusCode;
        try {
            statusCode = Integer.parseInt(tmiMessage.getArgs().get(0));
        } catch (NumberFormatException e) {
            return "Not a valid status code";
        }

        String statusCodeText = HttpStatus.valueOf(statusCode).toString();
        if (statusCodeText.contains("Unknown Status")) {
            return "Not a valid status code";
        }

        return String.format("%s https://http.cat/%s", statusCodeText, statusCode);
    }
}
