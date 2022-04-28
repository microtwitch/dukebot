package de.com.fdm.bot.commands.formula1

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.formula1.FormulaOneService
import de.com.fdm.twitch.tmi.TmiMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DriverStandingsCommand(
        @param:Autowired private val formulaOneService: FormulaOneService
) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        val data = formulaOneService.getStandings()

        return ""
    }
}