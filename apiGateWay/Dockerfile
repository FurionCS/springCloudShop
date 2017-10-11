FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/apigateway-0.0.1-SNAPSHOT.jar /apigateway.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/apigateway.jar"]
EXPOSE 8891