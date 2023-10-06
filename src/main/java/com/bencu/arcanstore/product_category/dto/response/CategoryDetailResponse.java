package com.bencu.arcanstore.product_category.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CategoryDetailResponse {
    UUID getId();
    String getName();
    LocalDateTime getCreatedAt();
}
