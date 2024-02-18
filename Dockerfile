FROM openjdk:17-alpine
LABEL authors="mackke"

# Set the working directory, where the application code and artifacts are stored within the container
WORKDIR /opt


CMD ["./gradlew", "clean", "bootJar"]

# Copy the build artifacts to the container
COPY build/libs/*.jar /opt/app.jar

ENV PORT 8080

# expose the port
EXPOSE 8080

# Define the entry point to run your application
ENTRYPOINT exec java -jar app.jar