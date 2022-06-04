package de.com.fdm.bot.commands.formula1

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.formula1.FormulaOneService
import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConstructorStandingsCommand(
    @param:Autowired private val formulaOneService: FormulaOneService
) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        val data = formulaOneService.getConstructorStandings()
        val standingsList = data?.mRData?.standingsTable?.standingsLists?.get(0) ?: return "Error fetching data"
        val result = StringBuilder()

        for (constructorStanding in standingsList.constructorStandings) {
            result.append(constructorStanding.position)
                .append(". ")
                .append(constructorStanding.constructor.name)
                .append(" (")
                .append(constructorStanding.points)
                .append(") | ")
        }

        return result.deleteCharAt(result.length-2).toString()
    }
}