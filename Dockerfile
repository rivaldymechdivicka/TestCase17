FROM openjdk:17-jdk-slim

RUN useradd -m -s /bin/bash appuser

WORKDIR /opt

COPY target/demo.jar /opt/app.jar
COPY src/main/resources/application.properties /opt/application.properties

RUN apt-get update && apt-get install -y mosquitto-clients

RUN chown -R appuser:appuser /opt

USER appuser

ENTRYPOINT ["java", "-jar", "/opt/app.jar", "--spring.config.location=file:/opt/application.properties"]
