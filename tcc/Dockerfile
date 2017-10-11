FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/tcc-0.0.1-SNAPSHOT.jar /tcc.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/tcc.jar"]
EXPOSE 8896