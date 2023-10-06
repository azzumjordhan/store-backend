package com.bencu.arcanstore.product_category.service;

import com.bencu.arcanstore.product_category.dto.request.ProductCategoryDto;
import com.bencu.arcanstore.product_category.dto.response.CategoryDetailResponse;
import com.bencu.arcanstore.product_category.model.ProductCategory;
import com.bencu.arcanstore.product_category.repo.ProductCategoryRepo;
import com.bencu.arcanstore.shared.model.ResponseData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepo productCategoryRepo;

    public ResponseEntity<ResponseData<List<CategoryDetailResponse>>> findAll() {
        ResponseData<List<CategoryDetailResponse>> responseData = new ResponseData<>();
        final List<ProductCategory> findData = productCategoryRepo.findAll();

        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        List<CategoryDetailResponse> responses = findData.stream().map(data -> projectionFactory.createProjection(CategoryDetailResponse.class, data)).toList();

        responseData.setStatus(true);
        responseData.setHttpStatus(HttpStatus.OK);
        responseData.setHttpStatusCode(200);
        responseData.setData(responses);
        return ResponseEntity.ok().body(responseData);
    }

    public ResponseEntity<ResponseData<CategoryDetailResponse>> getDetailCategory(UUID id) {
        ResponseData<CategoryDetailResponse> responseData = new ResponseData<>();

        final Optional<ProductCategory> findCategory = productCategoryRepo.findById(id);
        if (findCategory.isEmpty()) {
            responseData.setStatus(false);
            responseData.setHttpStatus(HttpStatus.NOT_FOUND);
            responseData.setHttpStatusCode(404);
            responseData.getMessages().add("Category Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        CategoryDetailResponse response = findCategory.map(data -> projectionFactory.createProjection(CategoryDetailResponse.class, data)).get();

        responseData.setStatus(true);
        responseData.setHttpStatus(HttpStatus.OK);
        responseData.setHttpStatusCode(200);
        responseData.setData(response);
        return ResponseEntity.ok().body(responseData);
    }

    public ResponseEntity<ResponseData<CategoryDetailResponse>> createCategory(ProductCategoryDto payload) {
        ResponseData<CategoryDetailResponse> responseData = new ResponseData<>();

        ProductCategory category = new ProductCategory();
        category.setName(payload.getName());

        final ProductCategory saveCategory = productCategoryRepo.save(category);
        final Optional<ProductCategory> getNewCategory = productCategoryRepo.findById(saveCategory.getId());

        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        CategoryDetailResponse response = getNewCategory.map(data -> projectionFactory.createProjection(CategoryDetailResponse.class, data)).get();

        responseData.setStatus(true);
        responseData.setHttpStatus(HttpStatus.OK);
        responseData.setHttpStatusCode(200);
        responseData.setData(response);
        return ResponseEntity.ok().body(responseData);
    }
}
