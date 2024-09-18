package org.pronsky.product_service.service.mapper;

import org.mapstruct.Mapper;
import org.pronsky.product_service.data.entities.Product;
import org.pronsky.product_service.service.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);
}
