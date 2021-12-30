package de.com.fdm.grpc.microsub.consumer;

import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.dispatcher.DispatcherClient;
import de.com.fdm.grpc.dispatcher.lib.OutboundMessage;
import de.com.fdm.grpc.microsub.lib.ConsumerGrpc;
import de.com.fdm.grpc.microsub.lib.Empty;
import de.com.fdm.grpc.microsub.lib.EventsubMessage;
import de.com.fdm.db.data.MicroSub;
import de.com.fdm.db.repositories.MicroSubRepository;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class MicrosubConsumerServiceImpl extends ConsumerGrpc.ConsumerImplBase {

    @Autowired
    private DispatcherClient dispatcherClient;

    @Autowired
    private ConfigProperties config;

    @Autowired
    private MicroSubRepository microSubRepository;

    @Override
    public void consume(EventsubMessage eventsubMessage, StreamObserver<Empty> responseOvserver) {
        Context ctx = Context.current().fork();

        List<MicroSub> microSubList = this.microSubRepository.findAllByBroadcasterUserId(eventsubMessage.getBroadcasterUserId());

        for (MicroSub microSub : microSubList) {
            if (microSub.getBroadcasterUserId().equals(eventsubMessage.getBroadcasterUserId())) {
                OutboundMessage msg = OutboundMessage
                        .newBuilder()
                        .setText(String.format("[%s]: %s", eventsubMessage.getBroadcasterUserName(), eventsubMessage.getEventUserName()))
                        .setChannel(microSub.getChannel())
                        .setAuth(config.getBotAuth())
                        .build();

                ctx.run(() -> this.dispatcherClient.send(msg));
            }
        }

        Empty response = Empty.newBuilder().build();
        responseOvserver.onNext(response);
        responseOvserver.onCompleted();
    }
}
