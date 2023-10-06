package com.bencu.arcanstore.product_subcategory.repo;

import com.bencu.arcanstore.product_subcategory.model.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductSubcategoryRepo extends JpaRepository<ProductSubcategory, UUID> {
}
