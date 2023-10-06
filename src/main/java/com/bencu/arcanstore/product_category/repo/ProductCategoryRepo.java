package com.bencu.arcanstore.product_category.repo;

import com.bencu.arcanstore.product_category.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, UUID> {
}
