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
        return getCommit();
    }

    private String getCommit() {
        Path filePath = Path.of("commit.txt");
        String commit;
        try {
            byte[] bytes = Files.readAllBytes(filePath);
            commit = new String (bytes);
        } catch (IOException e) {
            return "Error reading commit";
        }

        return commit;
    }
}
