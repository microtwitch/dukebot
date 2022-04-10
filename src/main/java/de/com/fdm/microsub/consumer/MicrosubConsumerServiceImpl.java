package de.com.fdm.microsub.consumer;

import de.com.fdm.config.ConfigProperties;
import de.com.fdm.grpc.microsub.lib.ConsumerGrpc;
import de.com.fdm.grpc.microsub.lib.Empty;
import de.com.fdm.grpc.microsub.lib.EventsubMessage;
import de.com.fdm.db.data.MicroSub;
import de.com.fdm.db.repositories.MicroSubRepository;
import de.com.fdm.grpc.microsub.lib.Type;
import de.com.fdm.twitch.tmi.OutboundMessage;
import de.com.fdm.twitch.tmi.TmiService;
import io.grpc.Context;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@GrpcService
public class MicrosubConsumerServiceImpl extends ConsumerGrpc.ConsumerImplBase {
    Logger logger = LoggerFactory.getLogger(MicrosubConsumerServiceImpl.class);
    private final Map<String, Instant> userLastAlertTime;

    @Autowired
    private TmiService tmiService;

    @Autowired
    private ConfigProperties config;

    @Autowired
    private MicroSubRepository microSubRepository;

    public MicrosubConsumerServiceImpl() {
        this.userLastAlertTime = new HashMap<>();
    }

    @Override
    public void consume(EventsubMessage eventsubMessage, StreamObserver<Empty> responseOvserver) {
        logger.info("Eventsub event received: {}", eventsubMessage);
        Context ctx = Context.current().fork();

        if (eventsubMessage.getBroadcasterUserId().equals("80805824")) {
            if (eventsubMessage.getEventType() == Type.FOLLOW) {
                ctx.run(() -> sendTurtoiseFollowAlert(eventsubMessage));
            }
            if (eventsubMessage.getEventType() == Type.SUB) {
                ctx.run(() -> sendTurtoiseSubAlert(eventsubMessage));
            }

            Empty response = Empty.newBuilder().build();
            responseOvserver.onNext(response);
            responseOvserver.onCompleted();
            return;
        }

        List<MicroSub> microSubList = this.microSubRepository.findAllByBroadcasterUserId(eventsubMessage.getBroadcasterUserId());

        for (MicroSub microSub : microSubList) {
            if (microSub.getBroadcasterUserId().equals(eventsubMessage.getBroadcasterUserId())) {
                String text = String.format("[%s]: %s", eventsubMessage.getBroadcasterUserName(), eventsubMessage.getEventUserName());

                OutboundMessage msg = new OutboundMessage(microSub.getChannel(), text);
                ctx.run(() -> this.tmiService.send(msg));
            }
        }

        Empty response = Empty.newBuilder().build();
        responseOvserver.onNext(response);
        responseOvserver.onCompleted();
    }

    private void sendTurtoiseFollowAlert(EventsubMessage eventsubMessage) {
        if (!canSendFollow(eventsubMessage)) {
            return;
        }
        String text = String.format("PagChomp OH SHIT %s THANKS FOR THE FOLLOW!", eventsubMessage.getEventUserName());

        OutboundMessage msg = new OutboundMessage("turtoise", text);

        tmiService.send(msg);
    }

    private void sendTurtoiseSubAlert(EventsubMessage eventsubMessage) {
        String text;
        if (eventsubMessage.getIsGift()) {
            text = String.format("PagChomp OH SHIT %s YOU JUST GOT GIFTED!", eventsubMessage.getEventUserName());
        } else {
            text = String.format("heCrazy YOOO %s THANKS FOR SUBBING!", eventsubMessage.getEventUserName());
        }

        OutboundMessage msg = new OutboundMessage("turtoise", text);

        tmiService.send(msg);
    }

    private boolean canSendFollow(EventsubMessage msg) {
        if (!userLastAlertTime.containsKey(msg.getEventUserId())) {
            userLastAlertTime.put(msg.getEventUserId(), Instant.now());
            return true;
        }

        long lastAlertTime = userLastAlertTime.get(msg.getEventUserId()).getEpochSecond();

        if ((Instant.now().getEpochSecond() - lastAlertTime) >= 60) {
            userLastAlertTime.put(msg.getEventUserId(), Instant.now());
            return true;
        }

        return false;
    }
}
