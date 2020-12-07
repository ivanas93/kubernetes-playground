# hello-world-server

hello-world-server is a project as an example of use gRPC protocol over Spring boot application. This project contains a
gRPC definition service ([HelloWorldService.proto](src/main/proto/HelloWorldService.proto)) and its java
implementation ([HelloWorldServerService.java](src/main/java/com/ivanas/helloworldserver/service/HelloWorldServerService.java))
.

hello-world-server **expose port 9898** to be called
by  [HelloWorldClientServiceImpl.java](../hello-world-client/src/main/java/com/ivanas/helloworldclient/service/HelloWorldClientServiceImpl.java)

## Configuration

### Application properties

```properties
spring.application.name=hello-world-server
grpc.server.port=9898
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
docker build -t hello-world-server .
```

### Run docker container

```bash
docker run -dit --rm --name hello-world-server -p9898:9898 hello-world-server
```


