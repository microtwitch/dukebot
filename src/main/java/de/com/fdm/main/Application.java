package de.com.fdm.main;


import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.receiver.ReceiverService;
import de.com.fdm.mongo.MicroSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages = "de.com.fdm.*")
@EnableMongoRepositories(basePackageClasses = MicroSubRepository.class)
public class Application {

    @Autowired
    private ReceiverService receiverService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void joinChannels() {
        receiverService.joinInitialChannels();
    }
}
