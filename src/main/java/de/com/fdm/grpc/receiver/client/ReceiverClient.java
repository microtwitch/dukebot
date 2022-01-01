package de.com.fdm.grpc.receiver.client;

import de.com.fdm.grpc.receiver.lib.ReceiverGrpc;
import de.com.fdm.grpc.receiver.lib.Registration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ReceiverClient {
    private final ReceiverGrpc.ReceiverStub asyncStub;

    @Autowired
    public ReceiverClient(@Qualifier("receiverHost") String receiverHost,
                          @Qualifier("receiverPort") int receiverPort) {
        this(ManagedChannelBuilder.forAddress(receiverHost, receiverPort)
                .usePlaintext());
    }

    private ReceiverClient(ManagedChannelBuilder<?> channelBuilder) {
        ManagedChannel channel = channelBuilder.build();
        this.asyncStub = ReceiverGrpc.newStub(channel);
    }

    public void register(Registration registration) {
        this.asyncStub.register(registration, new ReceiverCallback());
    }

    public void unsubscribe(Registration registration) {
        this.asyncStub.unsubscribe(registration, new ReceiverCallback());
    }
}
