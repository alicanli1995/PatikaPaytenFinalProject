FROM openjdk:17
ADD target/patika-grad-project.jar patika-grad-project.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "patika-grad-project.jar"]