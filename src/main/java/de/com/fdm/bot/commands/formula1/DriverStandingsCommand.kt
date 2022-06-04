package de.com.fdm.bot.commands.formula1

import de.com.fdm.bot.Command
import de.com.fdm.bot.api.formula1.FormulaOneService
import de.com.fdm.bot.api.twitch.tmi.TmiMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DriverStandingsCommand(
        @param:Autowired private val formulaOneService: FormulaOneService
) : Command {
    override fun execute(tmiMessage: TmiMessage): String {
        val data = formulaOneService.getStandings()
        val standingsList = data?.mRData?.standingsTable?.standingsLists?.get(0) ?: return "Error fetching data"
        val result = StringBuilder()

        var limit = 0
        for (driverStanding in standingsList.driverStandings) {
            result.append(driverStanding.position)
                .append(". ")
                .append(driverStanding.driver.givenName)
                .append(" ")
                .append(driverStanding.driver.familyName)
                .append(" (")
                .append(driverStanding.points)
                .append(") | ")

            limit++
            if (limit >= 10) {
                break
            }
        }

        return result.deleteCharAt(result.length-2).toString()
    }
}