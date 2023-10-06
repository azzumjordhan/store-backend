package com.bencu.arcanstore.product.model;

import com.bencu.arcanstore.product_sku.model.ProductSKU;
import com.bencu.arcanstore.product_subcategory.model.ProductSubcategory;
import com.bencu.arcanstore.shared.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_size")
    @Enumerated(EnumType.STRING)
    private ProductSize productSize;

    private String description;

    private Integer price;

    @Column(name = "ready_stock")
    private Integer readyStock;

    @Column(name = "total_stock")
    private Integer totalStock;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<ProductImage> productImage;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subcategory_id")
    private ProductSubcategory productSubcategory;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<ProductSKU> productSku;
}
