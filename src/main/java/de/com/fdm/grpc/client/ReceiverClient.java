package de.com.fdm.grpc.client;

import de.com.fdm.grpc.lib.ReceiverGrpc;
import de.com.fdm.grpc.lib.Registration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiverClient {
    private final ReceiverGrpc.ReceiverStub asyncStub;

    @Autowired
    public ReceiverClient(String receiverHost, int receiverPort) {
        this(ManagedChannelBuilder.forAddress(receiverHost, receiverPort)
                .usePlaintext());
    }

    private ReceiverClient(ManagedChannelBuilder<?> channelBuilder) {
        ManagedChannel channel = channelBuilder.build();
        this.asyncStub = ReceiverGrpc.newStub(channel);
    }

    public void register(Registration registration) {
        this.asyncStub.register(registration, new EmptyCallback());
    }
}
