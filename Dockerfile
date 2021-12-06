FROM adoptopenjdk/openjdk11
EXPOSE 8080 3031
ADD target/LongArythmeticsJDK11-1.0-SNAPSHOT.jar LA-11.jar
ENTRYPOINT ["java", "-jar", "LA-11.jar"]
