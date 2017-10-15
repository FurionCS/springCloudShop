FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/integral-0.0.1-SNAPSHOT.jar /integral.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/integral.jar"]
EXPOSE 8894