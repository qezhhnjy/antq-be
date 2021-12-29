FROM openjdk:14
VOLUME /tmp
ADD /target/antq-oauth-1.0-SNAPSHOT.jar  app.jar
ENTRYPOINT ["java","-Xms256m","-Xmx256m","-jar", "--spring.profiles.active=prod", "/app.jar"]