FROM eclipse-temurin
COPY ./target/group2-project-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group2-project-0.1.0.1-jar-with-dependencies.jar"]