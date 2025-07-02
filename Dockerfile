FROM openjdk:21-jdk-slim

COPY target/Food-Ordering-Website-0.0.1-SNAPSHOT.jar Food-Ordering-Website-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Food-Ordering-Website-0.0.1-SNAPSHOT.jar"]

