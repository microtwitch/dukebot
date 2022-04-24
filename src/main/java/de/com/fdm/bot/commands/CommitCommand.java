package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class CommitCommand implements Command {
    @Override
    public String execute(Parameters params) {
        return System.getenv("COMMIT_SHA");
    }
}
