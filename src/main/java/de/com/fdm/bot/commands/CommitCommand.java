package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.stereotype.Component;


@Component
public class CommitCommand implements Command {
    private final static String GITHUB_URL
            = "https://github.com/microtwitch/dukebot/commit/";

    @Override
    public String execute(TmiMessage tmiMessage) {
        String commitHash = System.getenv("COMMIT_SHA").substring(0, 7);
        return GITHUB_URL + commitHash;
    }
}
