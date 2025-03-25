## Use OpenJDK 21 as the base image
#FROM eclipse-temurin:21-jdk as build
#
## Set the working directory inside the container
#WORKDIR /app
#
## Copy the JAR file from the target directory
#COPY target/ReceiptProcessor.jar ReceiptProcessor.jar
#
## Expose port 8080
#EXPOSE 8080
#
## Run the Spring Boot application
#ENTRYPOINT ["java", "-jar", "ReceiptProcessor.jar"]

# Use OpenJDK 21 as the base image for building the application
FROM eclipse-temurin:21-jdk as build

# Set working directory
WORKDIR /app

# Copy Maven wrapper files and pom.xml first to leverage Docker cache
COPY pom.xml mvnw mvnw.cmd ./
COPY .mvn .mvn

# Download dependencies (cache layer)
RUN ./mvnw dependency:go-offline

# Copy the entire project
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a minimal JDK image for running the app
FROM eclipse-temurin:21-jdk as runtime

# Set working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/ReceiptProcessor-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
