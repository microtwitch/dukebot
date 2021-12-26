package de.com.fdm.grpc.dispatcher.client;


import de.com.fdm.grpc.dispatcher.lib.Empty;
import io.grpc.stub.StreamObserver;

public class DispatcherCallback implements StreamObserver<Empty> {
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
