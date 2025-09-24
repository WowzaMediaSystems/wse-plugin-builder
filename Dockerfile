FROM wowza/wowza-streaming-engine:latest-trial AS builder

#####################
# BUILDER STUFF
#####################
FROM openjdk:17.0.1-jdk-slim

COPY --from=builder /usr/local/WowzaStreamingEngine/lib.default /usr/local/WowzaStreamingEngine/lib

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
