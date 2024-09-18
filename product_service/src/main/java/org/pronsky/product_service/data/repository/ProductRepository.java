package org.pronsky.product_service.data.repository;

import org.pronsky.product_service.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
