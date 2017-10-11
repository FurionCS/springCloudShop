FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/hystrix-dashboard-0.0.1-SNAPSHOT.jar /hystrix-dashboard.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/hystrix-dashboard.jar"]
EXPOSE 8892