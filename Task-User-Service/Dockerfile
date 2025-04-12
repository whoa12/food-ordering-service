#Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

#Information of who maintains the image
LABEL maintainer="personal.com"

#Add the application's jar to the image
COPY target/Task-User-Service-0.0.1-SNAPSHOT.jar Task-User-Service-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java", "-jar", "Task-User-Service-0.01-SNAPSHOT.jar"]