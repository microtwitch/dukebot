package de.com.fdm.grpc.dispatcher;

import de.com.fdm.grpc.dispatcher.lib.DispatcherGrpc;
import de.com.fdm.grpc.dispatcher.lib.OutboundMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DispatcherClient {
    private final DispatcherGrpc.DispatcherStub asyncStub;

    @Autowired
    public DispatcherClient(@Qualifier("dispatcherHost") String dispatcherHost
            , @Qualifier("dispatcherPort") int dispatcherPort) {
        this(ManagedChannelBuilder.forAddress(dispatcherHost, dispatcherPort)
                .usePlaintext());
    }

    private DispatcherClient(ManagedChannelBuilder<?> channelBuilder) {
        ManagedChannel channel = channelBuilder.build();
        this.asyncStub = DispatcherGrpc.newStub(channel);
    }

    public void send(OutboundMessage msg) {
        this.asyncStub.send(msg, new DispatcherCallback());
    }
}
