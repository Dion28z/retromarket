# Retro Market API

## Description
Commerce Services


## Requirements
- Java 17
- Maven 3.9.11


## Stack
- Spring Boot 3.5.7
- MongoDb 5.6.1


## Clean
```
mvn clean
```
## Install
```
mvn clean install
```
## Run
```
mvn spring-boot:run -pl retromarketweb
```

## Debug
```
mvn spring-boot:run -pl retromarketweb -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```