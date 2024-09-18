package org.pronsky.product_details_service.service.mapper;

import org.mapstruct.Mapper;
import org.pronsky.product_details_service.data.entities.ProductDetails;
import org.pronsky.product_details_service.service.dto.ProductDetailsDto;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    ProductDetailsDto toDto(ProductDetails productDetails);

    ProductDetails toEntity(ProductDetailsDto productDetailsDto);
}
