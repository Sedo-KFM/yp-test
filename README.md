##### API: localhost:8080/api
##### Framework: Spring Boot
##### Java version: 19
---
##### Dockerfile setup:
```
FROM openjdk:19-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar", "com.kiriakov.magtrain.MagtrainApplication"]
```
