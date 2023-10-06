package com.bencu.arcanstore.product_subcategory.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductSubcategoryDto {
    String name;
    UUID categoryId;
}
