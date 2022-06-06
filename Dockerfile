FROM openjdk:17-alpine

# run the application as a non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080 8082

ARG JAR_FILE=./build/libs/missao-java-com-gradle-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
