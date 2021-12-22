FROM openjdk:11-jre
MAINTAINER GABRIEL
VOLUME /tmp
EXPOSE 8080
COPY target/exercise-0.0.1-SNAPSHOT.jar exercise.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/exercise-0.0.1-SNAPSHOT.jar"]