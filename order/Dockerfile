FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/order-0.0.1-SNAPSHOT.jar /order.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/order.jar"]
EXPOSE 8893