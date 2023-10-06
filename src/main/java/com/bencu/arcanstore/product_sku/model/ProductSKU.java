package com.bencu.arcanstore.product_sku.model;

import com.bencu.arcanstore.product.model.Product;
import com.bencu.arcanstore.shared.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_sku")
public class ProductSKU extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_code")
    private String productCode;

    private String sku;

    @Enumerated(EnumType.STRING)
    private SkuStatus status;

    @Column(name = "reff_id")
    private String reffId;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;
}
