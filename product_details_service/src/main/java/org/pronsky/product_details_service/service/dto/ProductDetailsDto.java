package org.pronsky.product_details_service.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetailsDto {
    private String id;
    private String name;
    private String description;
    private Double price;
}
