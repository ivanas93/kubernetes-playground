package com.ivanas.helloworldclient.service;

import com.ivanas.helloworldclient.service.HelloWorldServiceGrpc.HelloWorldServiceBlockingStub;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldClientServiceImpl implements HelloWorldClientService {

    @GrpcClient("hello-world-server")
    private HelloWorldServiceBlockingStub blockingStub;

    public String sendMessage(final String name) {
        try {
            final var response = this.blockingStub.sayHelloWorld(HelloWorldRequest.newBuilder()
                    .setParams(name)
                    .build());
            return response.getReturn();
        } catch (final StatusRuntimeException e) {
            return "Failed with " + e.getStatus().getCode().name();
        }
    }

}
