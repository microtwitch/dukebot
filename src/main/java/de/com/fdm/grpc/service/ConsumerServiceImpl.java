package de.com.fdm.grpc.service;

import de.com.fdm.bot.MessageHandler;
import de.com.fdm.grpc.lib.ConsumerGrpc;
import de.com.fdm.grpc.lib.Empty;
import de.com.fdm.grpc.lib.TwitchMessage;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ConsumerServiceImpl extends ConsumerGrpc.ConsumerImplBase {

    @Override
    public void consume(TwitchMessage request, StreamObserver<Empty> responseObserver) {
        MessageHandler.handleMessage(request);

        Empty response = Empty.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
