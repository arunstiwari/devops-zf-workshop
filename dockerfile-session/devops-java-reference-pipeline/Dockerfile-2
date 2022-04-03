ARG VERSION=11-jre-slim-sid
FROM openjdk:$VERSION
LABEL maintainer=arunstiwari@gmail.com
WORKDIR /home/userservice
COPY target/userservice.jar userservice.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "userservice.jar"]