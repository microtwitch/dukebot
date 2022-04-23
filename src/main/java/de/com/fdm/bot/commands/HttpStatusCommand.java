package de.com.fdm.bot.commands;


import org.springframework.http.HttpStatus;

public class HttpStatusCommand extends Command {
    public HttpStatusCommand(Parameters params) {
        super(params);
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

        String statusCodeText = HttpStatus.valueOf(statusCode).toString();
        if (statusCodeText.contains("Unknown Status")) {
            return "Not a valid status code";
        }

        return String.format("%s https://http.cat/%s", statusCodeText, statusCode);
    }
}
