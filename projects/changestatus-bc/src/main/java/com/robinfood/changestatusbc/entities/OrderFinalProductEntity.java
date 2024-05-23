package com.robinfood.changestatusbc.entities;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "order_final_products")
public class OrderFinalProductEntity {

    @NonNull
    private Long articleId;

    @NonNull
    private Long articleTypeId;

    @NonNull
    private Long brandId;

    @NonNull
    private Long brandMenuId;

    @NonNull
    private String brandName;

    @NonNull
    private BigDecimal basePrice;

    @NonNull
    private Long companyId;

    private LocalDateTime createdAt;

    @NonNull
    private Double discountPrice;

    @NonNull
    private Long finalProductCategoryId;

    @NonNull
    private String finalProductCategoryName;

    @NonNull
    private Long finalProductId;

    @NonNull
    private String finalProductName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String image;

    @NonNull
    private Long menuHallProductId;

    @NonNull
    private Long orderId;

    @NonNull
    private Double productsPrice;

    @NonNull
    private Integer quantity;

    @NonNull
    private Long sizeId;

    @NonNull
    private String sizeName;

    private String sku;

    @Column(name = "co2_compensation_price") 
    private BigDecimal co2Total;

    @Column(name = "co2_compensation_price_total")
    private BigDecimal co2CompensationPriceTotal;

    @NonNull
    private Double total;

    @NonNull
    private Double totalPriceNt;

    @NonNull
    private Double totalTaxPrice;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
