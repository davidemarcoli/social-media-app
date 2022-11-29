# create a dockerfile for spring boot application
FROM openjdk:18-alpine
COPY backend/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=prod"]