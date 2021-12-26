package de.com.fdm.grpc.receiver.client;


import de.com.fdm.grpc.receiver.lib.Empty;
import io.grpc.stub.StreamObserver;

public class ReceiverCallback implements StreamObserver<Empty> {
    @Override
    public void onNext(Empty value) {
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(t.getMessage());
    }

    @Override
    public void onCompleted() {
    }
}
