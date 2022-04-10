package de.com.fdm.bot.commands;



import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.time.Duration;

public class PingCommand extends Command {
    public PingCommand(String channel) {
        super(channel);
    }

    public String execute() {
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
