FROM openjdk:11
WORKDIR /app
COPY target/secondhand-0.0.1-SNAPSHOT.jar secondhand-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "secondhand-0.0.1-SNAPSHOT.jar"]
