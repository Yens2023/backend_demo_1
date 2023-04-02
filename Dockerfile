FROM openjdk:17-jdk-alpine3.14

EXPOSE 8080

ADD ./target/mycomerce-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]