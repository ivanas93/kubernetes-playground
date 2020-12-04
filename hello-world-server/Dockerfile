FROM adoptopenjdk:11-jre-hotspot
EXPOSE 9898
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} hello-world-server.jar
ENTRYPOINT ["java","-jar","/hello-world-server.jar"]