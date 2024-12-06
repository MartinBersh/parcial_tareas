FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/tareas-0.0.1-SNAPSHOT.jar tareas.jar


ENTRYPOINT ["java", "-jar", "tareas.jar"]