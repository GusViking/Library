FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Use a smaller base image for the runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar ./app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]