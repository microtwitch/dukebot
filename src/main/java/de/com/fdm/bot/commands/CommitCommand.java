package de.com.fdm.bot.commands;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import org.springframework.stereotype.Component;

@Component
public class CommitCommand implements Command {
    @Override
    public String execute(Parameters params) {
        System.out.println(System.getenv());

        return System.getenv("GIT_COMMIT");
    }
}
