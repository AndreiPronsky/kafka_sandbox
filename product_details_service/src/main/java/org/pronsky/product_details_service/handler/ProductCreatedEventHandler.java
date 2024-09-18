package org.pronsky.product_details_service.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.product_details_service.service.ProductDetailsService;
import org.pronsky.product_details_service.service.dto.ProductDetailsDto;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "product-created-event-topic")
public class ProductCreatedEventHandler {

    private final ProductDetailsService productService;
    private final ObjectMapper objectMapper;

    @KafkaHandler
    public void handle(String event) throws JsonProcessingException {
        log.info("Received message: {}", event);

        ProductDetailsDto productDetailsDto = objectMapper.readValue(event, ProductDetailsDto.class);
        try {
            productService.saveProductDetails(productDetailsDto);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
