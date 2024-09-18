FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/product_details_service*.jar ./app.jar
CMD ["java", "-jar", "app.jar"]