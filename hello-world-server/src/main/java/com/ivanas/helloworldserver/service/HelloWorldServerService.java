package com.ivanas.helloworldserver.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloWorldServerService extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void sayHelloWorld(final HelloWorldRequest request, final StreamObserver<HelloWorldReturn> observer) {
        var sayHelloWorld = HelloWorldReturn.newBuilder()
                .setReturn("Hello ->" + request.getParams())
                .build();
        observer.onNext(sayHelloWorld);
        observer.onCompleted();
    }
}
