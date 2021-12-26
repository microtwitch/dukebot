package de.com.fdm.bot;

import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.dispatcher.DispatcherClient;
import de.com.fdm.grpc.dispatcher.lib.OutboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {

    @Autowired
    private ConfigProperties config;

    @Autowired
    private DispatcherClient dispatcherClient;

    public void handleCommand(PingCommand cmd) {
        System.out.println(cmd);

        OutboundMessage msg = OutboundMessage.newBuilder()
                .setAuth(config.getBotAuth())
                .setChannel(cmd.getChannel())
                .setName(config.getBotName())
                .setText(cmd.execute())
                .build();

        dispatcherClient.send(msg);
    }
}
