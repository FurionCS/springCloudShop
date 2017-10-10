FROM java
MAINTAINER <Mr.Cheng>

COPY ./target/server-0.0.1-SNAPSHOT.jar /server.jar

ENTRYPOINT ["java","-jar","server.jar","--spring.profiles.active=peer1"]
EXPOSE 8888

