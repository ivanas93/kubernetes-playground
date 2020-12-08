# hello-world-client

hello-world-client is a project as an example of use gRPC protocol over Spring boot application. This project contains
an API
controller [HelloWorldClientController.java](src/main/java/com/ivanas/helloworldclient/controller/HelloWorldClientController.java)
, definition service [HelloWorldService.proto](src/main/proto/HelloWorldService.proto) and gRPC client implementation
[HelloWorldClientServiceImpl](src/main/java/com/ivanas/helloworldclient/service/HelloWorldClientServiceImpl.java).

hello-world-client expose port 8080 to be call by http request(GET). When controller is invoked, the client service gRPC
sent request
to [HelloWorldServerService.java](../hello-world-server/src/main/java/com/ivanas/helloworldserver/service/HelloWorldServerService.java)

## Configuration

### Application properties

There are different application properties depending on environment. For select application properties is necessary send
an environment variable (**JAVA_OPTS=-Dspring.profiles.active=local**). An example this is:
```properties
server.port=8080
spring.application.name=hello-world-client
grpc.client.hello-world-server.address=static://localhost:9898/
grpc.client.hello-world-server.enable-keep-alive=true
grpc.client.hello-world-server.keep-alive-without-calls=true
grpc.client.hello-world-server.negotiation-type=plaintext
```

### gRPC Java converter

To translate a proto file to a java class, use maven plugin

```xml

<build>
    <extensions>
        <extension>
            <groupId>kr.motd.maven</groupId>
            <artifactId>os-maven-plugin</artifactId>
            <version>1.6.2</version>
        </extension>
    </extensions>
    <plugins>
        <plugin>
            <groupId>org.xolstice.maven.plugins</groupId>
            <artifactId>protobuf-maven-plugin</artifactId>
            <version>0.6.1</version>
            <configuration>
                <protocArtifact>com.google.protobuf:protoc:3.12.0:exe:${os.detected.classifier}</protocArtifact>
                <pluginId>grpc-java</pluginId>
                <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.34.0:exe:${os.detected.classifier}</pluginArtifact>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                        <goal>compile-custom</goal>
                        <goal>test-compile</goal>
                        <goal>test-compile-custom</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

after, it is necessary compile the project for executing proto translate

```bash
mvn protobuf:compile
```

## Usage

```bash
mvn spring-boot:run
```

## Docker

### Build docker image from Dockerfile

```bash
docker build -t hello-world-client .
```

### Run docker container

```bash
docker run -dit --rm --name hello-world-client -p8080:8080 --env JAVA_OPTS=-Dspring.profiles.active=standalone hello-world-client
```

