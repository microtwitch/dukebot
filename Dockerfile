FROM gradle:7.3.3-jdk17-alpine AS builder

COPY settings.gradle .
COPY build.gradle .
COPY gradle gradle
COPY src src
COPY .git .git

RUN git rev-parse --short HEAD >> commit.txt
RUN gradle build

FROM bellsoft/liberica-openjdk-alpine:latest
COPY --from=builder /home/gradle/commit.txt commit.txt
COPY --from=builder /home/gradle/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
