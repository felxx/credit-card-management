FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar

ARG RABBITMQ_SERVER=rabbitmq
ARG EUREKA_SERVER=localhost

ENTRYPOINT java -jar app.jar
