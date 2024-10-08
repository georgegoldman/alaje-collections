#Build environment
FROM maven:3.9.8-eclipse-temurin-21-alpine AS mavenBuild
WORKDIR /build
COPY pom.xml .
COPY src ./src/
#COPY ./containers ./containers/

RUN mvn -B clean package -DskipTests
#Production packaging
FROM openjdk:21-jdk-slim
# containers

MAINTAINER John Adeshola timadeshola@gmail.com
WORKDIR /app
COPY --from=mavenBuild /build/target/foundation-service-0.0.1.jar /app/foundation-service-0.0.1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "foundation-service-0.0.1.jar"]

