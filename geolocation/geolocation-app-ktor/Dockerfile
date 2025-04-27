FROM bellsoft/liberica-openjdk-alpine:21 AS build
#Windows
ARG JAR_FILE=build/libs/*.jar
#Linux
#ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} geolocation-app-ktor-0.0.1.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "geolocation-app-ktor-0.0.1.jar"]