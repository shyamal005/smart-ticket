# Stage 1: Build the Application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the Application
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# This line finds ANY .jar file in target (safer than hardcoding the name)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
