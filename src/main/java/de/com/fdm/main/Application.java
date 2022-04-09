package de.com.fdm.main;


import de.com.fdm.db.repositories.MicroSubRepository;
import de.com.fdm.microsub.MicrosubService;
import de.com.fdm.tmi.TmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "de.com.fdm.*")
@EnableJpaRepositories(basePackageClasses = MicroSubRepository.class)
@EntityScan(basePackages = "de.com.fdm.db.data")
public class Application {

    @Autowired
    private TmiService tmiService;

    @Autowired
    private MicrosubService microsubService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void joinChannels() {
        tmiService.joinInitialChannels();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addTurtoiseAlerts() {
        microsubService.registerTurtoiseAlerts();
    }
}
