FROM openjdk:11
WORKDIR /wainer/app-transferenciawainer
COPY /build/libs/transferenciaWainer-0.1-all.jar /app.jar
#em tempo de build
ARG PORT_BUILD=8080
#em tempo de execucao
ENV PORT=$PORT_BUILD
EXPOSE $PORT_BUILD
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"]
CMD ["-jar","/app.jar"]

#FROM adoptopenjdk/openjdk13-openj9:jdk-13.0.2_8_openj9-0.18.0-alpine-slim
#COPY build/libs/*.jar transferenciaWainer.jar
#EXPOSE 8080
#CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-jar", "transferenciaWainer.jar"]
