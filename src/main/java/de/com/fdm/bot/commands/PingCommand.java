package de.com.fdm.bot.commands;


import de.com.fdm.bot.Command;
import de.com.fdm.bot.Parameters;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Duration;

@Component
public class PingCommand implements Command {
    public String execute(Parameters params) {
        return String.format("Uptime: %s", this.formatUptime(this.getUptime()));
    }

    private long getUptime() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        return rb.getUptime();
    }

    private String formatUptime(long uptime) {
        return Duration.ofMillis(uptime).toString().substring(2).toLowerCase();
    }
}
