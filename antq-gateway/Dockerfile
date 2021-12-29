FROM openjdk:14
VOLUME /tmp
ADD /target/antq-gateway-1.0-SNAPSHOT.jar  app.jar
ENTRYPOINT ["java","-Xms256m","-Xmx256m","-jar", "/app.jar", "--spring.profiles.active=prod"]