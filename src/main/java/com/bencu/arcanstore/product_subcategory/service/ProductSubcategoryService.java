package com.bencu.arcanstore.product_subcategory.service;

import com.bencu.arcanstore.product_category.model.ProductCategory;
import com.bencu.arcanstore.product_category.repo.ProductCategoryRepo;
import com.bencu.arcanstore.product_subcategory.dto.request.ProductSubcategoryDto;
import com.bencu.arcanstore.product_subcategory.dto.response.SubcategoryResponse;
import com.bencu.arcanstore.product_subcategory.model.ProductSubcategory;
import com.bencu.arcanstore.product_subcategory.repo.ProductSubcategoryRepo;
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
public class ProductSubcategoryService {
    private final ProductSubcategoryRepo subcategoryRepo;
    private final ProductCategoryRepo categoryRepo;

    public ResponseEntity<ResponseData<List<SubcategoryResponse>>> getListSubcategory() {
        ResponseData<List<SubcategoryResponse>> responseData = new ResponseData<>();

        final List<ProductSubcategory> findAll = subcategoryRepo.findAll();

        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        List<SubcategoryResponse> responses = findAll.stream().map(data -> projectionFactory.createProjection(SubcategoryResponse.class, data)).toList();

        responseData.setStatus(true);
        responseData.setHttpStatus(HttpStatus.OK);
        responseData.setHttpStatusCode(200);
        responseData.setData(responses);
        return ResponseEntity.ok().body(responseData);
    }

    public ResponseEntity<ResponseData<SubcategoryResponse>> getDetailSubcategory(UUID id) {
        ResponseData<SubcategoryResponse> responseData = new ResponseData<>();

        final Optional<ProductSubcategory> getDetail = subcategoryRepo.findById(id);
        if (getDetail.isEmpty()) {
            responseData.setStatus(false);
            responseData.setHttpStatus(HttpStatus.NOT_FOUND);
            responseData.setHttpStatusCode(404);
            responseData.getMessages().add("Data Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        SubcategoryResponse response = getDetail.map(data -> projectionFactory.createProjection(SubcategoryResponse.class, data)).get();

        responseData.setStatus(true);
        responseData.setHttpStatus(HttpStatus.OK);
        responseData.setHttpStatusCode(200);
        responseData.setData(response);
        return ResponseEntity.ok().body(responseData);
    }

    public ResponseEntity<ResponseData<SubcategoryResponse>> createSubcategory(ProductSubcategoryDto payload) {
        ResponseData<SubcategoryResponse> responseData = new ResponseData<>();

        final Optional<ProductCategory> checkCategory = categoryRepo.findById(payload.getCategoryId());
        if (checkCategory.isEmpty()) {
            responseData.setStatus(false);
            responseData.setHttpStatus(HttpStatus.NOT_FOUND);
            responseData.setHttpStatusCode(404);
            responseData.getMessages().add("Category not found");
        }

        ProductSubcategory subcategory = new ProductSubcategory();
        subcategory.setName(payload.getName());
        subcategory.setProductCategory(checkCategory.get());
        final ProductSubcategory saveSubcategory = subcategoryRepo.save(subcategory);

        Optional<ProductSubcategory> getNewSubcategory = subcategoryRepo.findById(saveSubcategory.getId());
        ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();
        SubcategoryResponse response = getNewSubcategory.map(data -> projectionFactory.createProjection(SubcategoryResponse.class, data)).get();

        responseData.setStatus(true);
        responseData.setHttpStatus(HttpStatus.OK);
        responseData.setHttpStatusCode(200);
        responseData.setData(response);
        return ResponseEntity.ok().body(responseData);
    }
}
