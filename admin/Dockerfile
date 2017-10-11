FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/admin-0.0.1-SNAPSHOT.jar /admin.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/admin.jar"]
EXPOSE 8890