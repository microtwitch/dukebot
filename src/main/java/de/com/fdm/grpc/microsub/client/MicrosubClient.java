package de.com.fdm.grpc.microsub.client;

import de.com.fdm.grpc.microsub.lib.Deletion;
import de.com.fdm.grpc.microsub.lib.MicrosubGrpc;
import de.com.fdm.grpc.microsub.lib.Registration;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MicrosubClient {
   private final MicrosubGrpc.MicrosubStub asyncStub;

   @Autowired
   public MicrosubClient(@Qualifier("microsubHost") String microsubHost, @Qualifier("microsubPort") int microsubPort) {
       this(ManagedChannelBuilder.forAddress(microsubHost, microsubPort).usePlaintext());
   }

   private MicrosubClient(ManagedChannelBuilder<?> channelBuilder) {
       ManagedChannel channel = channelBuilder.build();
       this.asyncStub = MicrosubGrpc.newStub(channel);
   }

   public void register(Registration registration) {
       this.asyncStub.register(registration, new MicrosubCallback());
   }

   public void delete(Deletion deletion) {
       this.asyncStub.delete(deletion, new MicrosubCallback());
   }
}
