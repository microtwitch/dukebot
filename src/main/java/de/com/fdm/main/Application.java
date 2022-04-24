package de.com.fdm.main;

import de.com.fdm.twitch.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication(scanBasePackages = "de.com.fdm.*")
public class Application {
    private final TmiService tmiService;
    private final String[] channels;

    public Application(
            @Autowired TmiService tmiService,
            @Value("${bot.channels}") String[] channels
    ) {
        this.tmiService = tmiService;
        this.channels = channels;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void joinChannels() {
        tmiService.joinChannels(channels);
    }
}
