package com.bencu.arcanstore.product.repo;

import com.bencu.arcanstore.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
}
