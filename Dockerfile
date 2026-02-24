FROM wowza/wowza-streaming-engine:latest-slim AS builder

RUN echo "$DOCKER_IMAGE_VERSION" > /tmp/DOCKER_IMAGE_VERSION

#####################
# BUILDER STUFF
#####################
FROM openjdk:17.0.1-jdk-slim

COPY --from=builder /usr/local/WowzaStreamingEngine/lib.default /usr/local/WowzaStreamingEngine/lib
COPY --from=builder /usr/local/WowzaStreamingEngine/conf.default /usr/local/WowzaStreamingEngine/conf
COPY --from=builder /tmp/DOCKER_IMAGE_VERSION /tmp/DOCKER_IMAGE_VERSION

RUN apt update
RUN apt install vim gradle -y

RUN adduser builder
USER builder
COPY --chown=builder:builder /builder /home/builder

#seed/start/download gradle stuff make things faster
RUN /home/builder/gradlew

ENV GRADLECMD=build
ENV INTERACTIVE=true

CMD ["/home/builder/build.sh"]
