package org.pronsky.product_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.pronsky.product_service.service.dto.ProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto) throws ExecutionException, InterruptedException, JsonProcessingException;
}
