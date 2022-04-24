package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class CommitCommand implements Command {
    private final static String GITHUB_URL
            = "https://github.com/microtwitch/dukebot/commit/";

    @Override
    public String execute(Parameters params) {
        String commitHash = System.getenv("COMMIT_SHA").substring(0, 7);
        return GITHUB_URL + commitHash;
    }
}
