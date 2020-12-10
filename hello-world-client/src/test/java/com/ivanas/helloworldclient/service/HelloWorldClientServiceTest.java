package com.ivanas.helloworldclient.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerFactoryAutoConfiguration;
import net.devh.boot.grpc.server.service.GrpcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=hello-world-client",
        "grpc.server.port=9898",
        "grpc.client.chatService.address=static://localhost:9898/"
})
@SpringJUnitConfig(classes = {MyComponentIntegrationTestConfiguration.class})
@DirtiesContext
class HelloWorldClientServiceTest {
    @Qualifier("test")
    @Autowired
    private HelloWorldClientService helloWorldClientService;

    @Test
    @DirtiesContext
    void testSayHello() {
        assertThat(helloWorldClientService.sendMessage("ThisIsMyName")).contains("Hello ->ThisIsMyName");
    }
}

@Configuration
@ImportAutoConfiguration({
        GrpcServerAutoConfiguration.class,
        GrpcServerFactoryAutoConfiguration.class,
        GrpcClientAutoConfiguration.class})
class MyComponentIntegrationTestConfiguration {

    @Bean(name = "test")
    HelloWorldClientService getHelloWorldClientService() {
        return new HelloWorldClientServiceImpl();
    }

    @Bean
    ServiceIntegrationTest getServiceIntegrationTest() {
        return new ServiceIntegrationTest();
    }

}

@GrpcService
class ServiceIntegrationTest extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {


    @Override
    public void sayHelloWorld(final HelloWorldRequest request, final StreamObserver<HelloWorldReturn> observer) {
        var sayHelloWorld = HelloWorldReturn.newBuilder()
                .setReturn("Hello ->" + request.getParams())
                .build();
        observer.onNext(sayHelloWorld);
        observer.onCompleted();
    }

}



