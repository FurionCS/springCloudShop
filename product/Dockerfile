FROM java
MAINTAINER <Mr.Cheng>
VOLUME /tmp
COPY ./target/product-0.0.1-SNAPSHOT.jar /product.jar
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","/product.jar"]
EXPOSE 8895