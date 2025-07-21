FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar

ARG EUREKA_SERVER=localhost
ARG KEYCLOAK_SERVER=localhost
ARG KEYCLOAK_PORT=8080

ENTRYPOINT java -jar app.jar
