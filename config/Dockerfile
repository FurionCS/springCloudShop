FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/config-0.0.1-SNAPSHOT.jar /config.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/config.jar"]
EXPOSE 8889