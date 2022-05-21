package de.com.fdm.bot.commands

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.github.GithubService
import de.com.fdm.bot.api.watchtower.WatchtowerService
import de.com.fdm.twitch.tmi.TmiMessage
import de.com.fdm.twitch.tmi.TmiService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UpdateCommand(
        @param:Autowired private val watchtowerService: WatchtowerService,
        @param:Autowired private val tmiService: TmiService,
        @param:Autowired private val githubService: GithubService
) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        val currentHash = System.getenv("COMMIT_SHA").substring(0, 7)
        val latestHash = githubService.getLatestCommit()

        println(currentHash)
        println(latestHash)

        if (currentHash == latestHash) {
            return "Already on latest commit!"
        }

        tmiService.send(tmiMessage.channel, "Updating...")
        watchtowerService.update()
        return ""
    }
}