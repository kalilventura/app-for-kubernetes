FROM gradle:8.10.0-jdk17-alpine AS builder

ARG HOME_DIR="/home/app"

RUN addgroup -S -g 1001 xyz \
    && adduser -S -u 1001 -h $HOME_DIR -G xyz app
WORKDIR $HOME_DIR

COPY src src/
COPY build.gradle settings.gradle ./

RUN chown -R app:xyz $HOME_DIR
USER app

RUN gradle --no-daemon clean bootJar

FROM amazoncorretto:22.0.2-alpine3.19

RUN apk add --no-cache curl

ARG HOME_DIR="/home/app"
RUN addgroup -S -g 1001 xyz \
    && adduser -S -u 1001 -h $HOME_DIR -G xyz app

WORKDIR $HOME_DIR
COPY --from=builder $HOME_DIR/build/libs/pizzaria-0.0.1.jar ./

RUN chown -R app:xyz $HOME_DIR

USER app

CMD [ "java", "-jar", "/home/app/pizzaria-0.0.1.jar" ]
