package com.bencu.arcanstore.product_subcategory.model;

import com.bencu.arcanstore.product.model.Product;
import com.bencu.arcanstore.product_category.model.ProductCategory;
import com.bencu.arcanstore.shared.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_subcategory")
public class ProductSubcategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private List<Product> products;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

}
