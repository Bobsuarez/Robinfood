package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "order_final_products")
public class OrderFinalProductEntity {

    @NonNull
    private Short articleId;

    @NonNull
    private Short articleTypeId;

    @NonNull
    private Short brandId;

    @NonNull
    private Short brandMenuId;

    @NonNull
    private String brandName;

    @NonNull
    private BigDecimal basePrice;

    @NonNull
    private Short companyId;

    private LocalDateTime createdAt;

    @NonNull
    private Double discountPrice;

    @NonNull
    private Short finalProductCategoryId;

    @NonNull
    private String finalProductCategoryName;

    @NonNull
    private Short finalProductId;

    @NonNull
    private String finalProductName;

    @Id
    private Long id;

    @NonNull
    private String image;

    @NonNull
    private Long menuHallProductId;

    @NonNull
    private Integer orderId;

    @NonNull
    private Double productsPrice;

    @NonNull
    private Short quantity;

    @NonNull
    private Short sizeId;

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
}
