FROM adoptopenjdk:11-jdk-hotspot

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/

RUN mvn package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/dashboard-0.0.1-SNAPSHOT.jar"]
