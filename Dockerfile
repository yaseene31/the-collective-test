FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
RUN mkdir logs && chown -R spring:spring logs && chmod -R 775 logs
USER spring:spring
EXPOSE 8080
ARG JAR_FILE=target/the-collective-test-1.0.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
