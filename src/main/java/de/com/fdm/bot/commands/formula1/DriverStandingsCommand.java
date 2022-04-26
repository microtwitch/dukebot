package de.com.fdm.bot.commands.formula1;

import de.com.fdm.bot.Command;
import de.com.fdm.bot.api.formula1.FormulaOneService;
import de.com.fdm.bot.api.formula1.data.DriverStanding;
import de.com.fdm.bot.api.formula1.data.MRData;
import de.com.fdm.bot.api.formula1.data.StandingsList;
import de.com.fdm.twitch.tmi.TmiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverStandingsCommand implements Command {
    private final FormulaOneService formulaOneService;

    public DriverStandingsCommand(
            @Autowired FormulaOneService formulaOneService
    ) {
        this.formulaOneService = formulaOneService;
    }

    public String execute(TmiMessage tmiMessage) {
        MRData data = formulaOneService.getDriverStandings();
        if (data == null) {
            return "Error retrieving standings";
        }

        StringBuilder result = new StringBuilder();

        StandingsList standings = data.StandingsTable.StandingsList;

        for (DriverStanding standing : standings.DriverStanding) {

        }

        return result.toString();
    }
}
