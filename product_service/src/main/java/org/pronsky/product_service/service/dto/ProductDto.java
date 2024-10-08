package org.pronsky.product_service.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
