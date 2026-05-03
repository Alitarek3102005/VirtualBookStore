
FROM eclipse-temurin:21-jdk-alpine

LABEL maintainer="AliTarek"
LABEL application="VirtualBookStore-Microservice"

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]