# Use a multi-stage build to build and package the React app
FROM node:14 AS frontend-build
WORKDIR /app
COPY src/main/resources/dashboard/package*.json ./
RUN npm install
COPY src/main/resources/dashboard/ ./
RUN npm run build

# Use an OpenJDK 11 image as the base for the Spring Boot application
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory to /app
WORKDIR /app

# Copy the React app build output into the Spring Boot app's static resources directory
COPY --from=frontend-build /app/build/ /app/src/main/resources/static/
RUN apt-get update && apt-get install -y maven
# Copy the Maven POM file and download dependencies
COPY pom.xml ./
RUN mvn -B dependency:go-offline -DskipTests=true

# Copy the application source code
COPY src/ ./src/

# Package the application as a JAR file
RUN mvn -B package -DskipTests=true

# Since spring boot app is configured to run in port 5000
EXPOSE 5000

# Set the command to run the application when the container starts
CMD ["java", "-jar", "target/my-app.jar"]
