package com.bencu.arcanstore.product_subcategory.dto.response;

import com.bencu.arcanstore.product_category.dto.response.CategoryDetailResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SubcategoryResponse {
    UUID getId();
    String getName();
    LocalDateTime getCreatedAt();

    CategoryDetailResponse getProductCategory();
}
