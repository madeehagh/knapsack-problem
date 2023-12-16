Dockerfile
# Use a base image with JDK 18 and Maven installed
FROM maven:3-openjdk-18 as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the project files to the container
COPY . /app

# Build the project with Maven
RUN mvn clean package

# Use a lightweight base image for the final image
FROM adoptopenjdk:18-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/knapsack.jar /app/knapsack.jar

# Specify the command to run when the container starts
CMD ["java", "-jar", "knapsack.jar"]
202