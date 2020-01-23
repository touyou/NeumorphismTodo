FROM openjdk:13-jdk-alpine

VOLUME /tmp

RUN mkdir /app
WORKDIR /app

RUN adduser -S sboot
USER sboot

ENV JAR_TARGET "Todo-0.0.1-SNAPSHOT.jar"

ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker build/libs/${JAR_TARGET}"]