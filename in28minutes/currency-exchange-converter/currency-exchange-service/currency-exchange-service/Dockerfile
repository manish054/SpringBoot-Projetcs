FROM openjdk:22-jdk
EXPOSE 8000
ADD target/currency-exchange-service.jar currency-exchange-service.jar
ENTRYPOINT ["java","-jar","/currency-exchange-service.jar"]
