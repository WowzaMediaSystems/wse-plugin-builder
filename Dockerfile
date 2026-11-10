FROM wowza/wowza-streaming-engine:latest-slim AS builder

RUN echo "$DOCKER_IMAGE_VERSION" > /tmp/DOCKER_IMAGE_VERSION

#####################
# BUILDER STUFF
#####################
FROM eclipse-temurin:17-jdk

COPY --from=builder /usr/local/WowzaStreamingEngine/lib.default /usr/local/WowzaStreamingEngine/lib
COPY --from=builder /usr/local/WowzaStreamingEngine/conf.default /usr/local/WowzaStreamingEngine/conf
COPY --from=builder /tmp/DOCKER_IMAGE_VERSION /tmp/DOCKER_IMAGE_VERSION

RUN apt-get update && apt-get install -y --no-install-recommends vim && rm -rf /var/lib/apt/lists/*

RUN useradd -m -s /bin/bash builder
USER builder
COPY --chown=builder:builder /builder /home/builder

#seed/start/download gradle stuff make things faster
RUN /home/builder/gradlew

ENV GRADLECMD=build
ENV INTERACTIVE=true

CMD ["/home/builder/build.sh"]
