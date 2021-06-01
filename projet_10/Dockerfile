FROM openjdk:8-jdk-alpine

# The application's jar file
ARG JAR_FILE=target/projet_7-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} projet_7.jar

#Then replace the run line with

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/projet_7.jar"]