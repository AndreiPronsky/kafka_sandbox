package org.pronsky.product_details_service.data.repository;

import org.pronsky.product_details_service.data.entities.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, UUID> {
}
