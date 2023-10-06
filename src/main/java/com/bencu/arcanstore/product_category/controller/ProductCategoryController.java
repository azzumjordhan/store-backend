package com.bencu.arcanstore.product_category.controller;

import com.bencu.arcanstore.product_category.dto.request.ProductCategoryDto;
import com.bencu.arcanstore.product_category.dto.response.CategoryDetailResponse;
import com.bencu.arcanstore.product_category.model.ProductCategory;
import com.bencu.arcanstore.product_category.service.ProductCategoryService;
import com.bencu.arcanstore.shared.model.ResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-category")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<CategoryDetailResponse>>> getListCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseData<CategoryDetailResponse>> getDetailCategory(@PathVariable("id") UUID id) {
        return categoryService.getDetailCategory(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData<CategoryDetailResponse>> create(@Valid @RequestBody ProductCategoryDto body) {
        return categoryService.createCategory(body);
    }
}
