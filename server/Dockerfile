FROM java
MAINTAINER <Mr.Cheng>

VOLUME /tmp
COPY ./target/server-0.0.1-SNAPSHOT.jar /server.jar

ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/server.jar"]
#CMD [ "--spring.profiles.active=peer1" ]
EXPOSE 8888

