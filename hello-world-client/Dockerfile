FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} hello-world-client.jar
ENTRYPOINT ["java","-jar","/hello-world-client.jar"]
