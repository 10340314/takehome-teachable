FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /takehomeassessment
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /takehomeassessment/target/takehomeassessment-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]