package org.pronsky.product_details_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ProductDetailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductDetailsApplication.class, args);
    }

}
