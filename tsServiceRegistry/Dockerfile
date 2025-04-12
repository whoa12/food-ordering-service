FROM openjdk:21-jdk-slim
LABEL maintainer = "personal.com"
COPY target/tsServiceRegistry-0.0.1-SNAPSHOT.jar tsServiceRegistry-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "tsServiceRegistry-0.0.1-SNAPSHOT.jar"]
EXPOSE 8083

