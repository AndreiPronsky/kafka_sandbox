#FROM maven:3.8.5-openjdk-17-slim AS build
#WORKDIR /product_service
#COPY pom.xml ./
#COPY src ./src
#RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/product_service*.jar ./app.jar
CMD ["java", "-jar", "app.jar"]