# superhero
Application to maintain superheroes

#Documentation

The documentation of the api: https://github.com/Piperocho/superhero/blob/master/src/main/resources/api/openapi.yaml

#How to use

mvn clean install

docker build -t superhero-test .

docker run -it -p 8080:8080 superhero-test

