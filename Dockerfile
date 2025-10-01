FROM openjdk:24
COPY ./target/group2-project-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group2-project-1.0-SNAPSHOT-jar-with-dependencies.jar"]