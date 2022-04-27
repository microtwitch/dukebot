package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.twitch.tmi.TmiMessage
import org.springframework.stereotype.Component

@Component
class CommitCommand : Command {
    private val githubUrl = "https://github.com/microtwitch/dukebot/commit/"

    override fun execute(tmiMessage: TmiMessage): String {
        val commitHash = System.getenv("COMMIT_SHA").substring(0, 7)
        return githubUrl + commitHash
    }
}