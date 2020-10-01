From openjdk:8
copy ./target/gateway-service-0.0.1-SNAPSHOT.jar gateway-service-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","gateway-service-0.0.1-SNAPSHOT.jar"]