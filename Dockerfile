FROM openjdk:17

COPY rest/target/rest-1.0-SNAPSHOT.jar bootcampapp.jar

ENTRYPOINT ["java", "-jar", "/bootcampapp.jar"]