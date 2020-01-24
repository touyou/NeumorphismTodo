FROM openjdk:13-jdk-alpine

VOLUME /tmp

RUN mkdir /app
WORKDIR /app

RUN adduser -S sboot
USER sboot

ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker ./build/libs/${JAR_TARGET}"]