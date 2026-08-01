# ==========================
# Stage 1 - BUILD
# ==========================
FROM maven:3.9.11-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests -B


RUN ls -lah /app/target

# ==========================
# Stage 2 - RUNTIME
# ==========================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/target/library-0.0.1-SNAPSHOT.jar app.jar


RUN ls -lah

ENV SERVER_PORT=8080

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]