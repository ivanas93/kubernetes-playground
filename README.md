# kubernetes-playground

The objective of this project is exemplify the use of different part of microservice architecture.

## Api-gateway as entrypoint

The entrypoint of microservice architecture is [Kong](https://github.com/Kong/kubernetes-ingress-controller). Kong
unifies all requests that entry the system.

## Microservices

Currently, there are two microservice one client and one server. Inter-service communication is across a binary protocol
gRPC:

- [Client](hello-world-client/README.md)
- [Server](hello-world-server/README.md)

### Run microservices in local environment without docker-compose

```shell
cd hello-world-client
docker build -t hello-world-client .
docker run -dit --rm --name hello-world-client --network host -p8080:8080 --env JAVA_OPTS=-Dspring.profiles.active=standalone hello-world-client
cd ../hello-world-server
docker build -t hello-world-server .
docker run -dit --rm --name hello-world-server --network host -p9898:9898 hello-world-server
GET http://localhost:8080/message?name=ivanas93
```

### Run microservices in local environment with docker-compose

```shell
cd hello-world-client
docker build -t hello-world-client .
cd ../hello-world-server
docker build -t hello-world-server .
docker-compose up
GET http://localhost:8080/message?name=ivanas93
```