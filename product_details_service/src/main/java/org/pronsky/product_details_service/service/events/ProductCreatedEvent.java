package org.pronsky.product_details_service.service.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCreatedEvent {
    private String id;
    private String name;
    private String description;
    private Double price;
}
