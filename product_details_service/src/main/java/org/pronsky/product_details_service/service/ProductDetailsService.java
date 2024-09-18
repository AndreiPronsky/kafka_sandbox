package org.pronsky.product_details_service.service;

import org.pronsky.product_details_service.service.dto.ProductDetailsDto;

public interface ProductDetailsService {
    ProductDetailsDto saveProductDetails(ProductDetailsDto productDetailsDto);
}
