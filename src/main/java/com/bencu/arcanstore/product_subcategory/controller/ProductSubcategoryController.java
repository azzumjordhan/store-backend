package com.bencu.arcanstore.product_subcategory.controller;

import com.bencu.arcanstore.product_subcategory.dto.request.ProductSubcategoryDto;
import com.bencu.arcanstore.product_subcategory.dto.response.SubcategoryResponse;
import com.bencu.arcanstore.product_subcategory.service.ProductSubcategoryService;
import com.bencu.arcanstore.shared.model.ResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-subcategory")
public class ProductSubcategoryController {
    private final ProductSubcategoryService subcategoryService;

    @GetMapping("/list")
    public ResponseEntity<ResponseData<List<SubcategoryResponse>>> getListSubcategory() {
        return subcategoryService.getListSubcategory();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseData<SubcategoryResponse>> getDetailSubcategory(@Valid @PathVariable("id") UUID id) {
        return subcategoryService.getDetailSubcategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData<SubcategoryResponse>> addProductSubcategory(@RequestBody ProductSubcategoryDto body) {
        return subcategoryService.createSubcategory(body);
    }
}
