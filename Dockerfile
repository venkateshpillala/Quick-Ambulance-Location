# ----------- Stage 1: Build the JAR file -------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy only pom.xml and download dependencies (use Docker cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the code and build the project
COPY . .
RUN mvn clean package -DskipTests

# ----------- Stage 2: Run the JAR file -------------
FROM openjdk:17-jdk-slim

# Create app directory
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8084
# or whatever port you use
# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
