FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/user-0.0.1-SNAPSHOT.jar /user.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/user.jar"]
EXPOSE 8894