package de.com.fdm.grpc.receiver.service;

import de.com.fdm.bot.MessageHandler;
import de.com.fdm.grpc.receiver.lib.ConsumerGrpc;
import de.com.fdm.grpc.receiver.lib.Empty;
import de.com.fdm.grpc.receiver.lib.TwitchMessage;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class ConsumerServiceImpl extends ConsumerGrpc.ConsumerImplBase {

    @Autowired
    private MessageHandler messageHandler;

    @Override
    public void consume(TwitchMessage request, StreamObserver<Empty> responseObserver) {
        // needed, because outbound grpc requests inherit deadline of inbound requests
        Context ctx = Context.current().fork();

        ctx.run(() -> this.messageHandler.handleMessage(request));

        Empty response = Empty.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
