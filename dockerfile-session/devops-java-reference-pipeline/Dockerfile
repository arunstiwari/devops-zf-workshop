ARG VERSION=11-jre-slim-sid
ARG MAVEN_VERSION=3.8.4-openjdk-11-slim
# First Stage
FROM maven:$MAVEN_VERSION AS builder
LABEL maintainer=arunstiwari@gmail.com
# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package

# Second stage : minimal runtime environment
FROM openjdk:$VERSION
LABEL maintainer=arunstiwari@gmail.com
WORKDIR /home/userservice
#COPY jar from the first stage
COPY --from=builder target/userservice.jar userservice.jar
#COPY target/userservice.jar userservice.jar

EXPOSE 80

ENTRYPOINT ["java", "-jar", "userservice.jar"]

