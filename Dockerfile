FROM openjdk:11-jre-slim
MAINTAINER carlos ventoso
COPY target/superhero-1.0.0.jar superhero-1.0.0.jar
ENTRYPOINT ["java","-jar","/superhero-1.0.0.jar"]