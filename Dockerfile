
FROM eclipse-temurin:21-jdk

WORKDIR /user-service

COPY target/user-service-0.0.1-SNAPSHOT.jar user-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "user-service.jar"]


