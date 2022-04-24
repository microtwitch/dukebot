FROM gradle:7.3.3-jdk17-alpine AS builder

COPY settings.gradle .
COPY build.gradle .
COPY gradle gradle
COPY src src

RUN gradle build

FROM bellsoft/liberica-openjdk-alpine:latest
RUN export GIT_COMMIT=$(git rev-parse --short HEAD)
COPY --from=builder /home/gradle/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
