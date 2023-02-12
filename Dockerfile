FROM openjdk:11-jre-slim
MAINTAINER carlos ventoso
COPY target/superhero-0.0.1-SNAPSHOT.jar superhero-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/superhero-0.0.1-SNAPSHOT.jar"]