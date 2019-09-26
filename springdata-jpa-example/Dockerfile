FROM openjdk:8
ADD target/springdata-jpa-example-0.0.1-SNAPSHOT.jar student-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "student-service.jar"]