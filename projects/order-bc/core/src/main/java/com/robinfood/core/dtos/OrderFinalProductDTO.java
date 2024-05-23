package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
public class OrderFinalProductDTO {
    
    private Long articleId;

    private Long articleTypeId;

    private Long brandId;

    private Long brandMenuId;

    private String brandName;

    private BigDecimal basePrice;

    private Long companyId;

    private LocalDateTime createdAt;

    private Double discountPrice;

    private Long finalProductCategoryId;

    private String finalProductCategoryName;

    private Long finalProductId;

    private String finalProductName;

    private Long id;

    private String image;

    private Long orderId;

    private Double productsPrice;

    private Integer quantity;

    private Long sizeId;

    private String sizeName;

    private BigDecimal co2Total;

    private Double totalPriceNt;

    private Double totalTaxPrice;

    private LocalDateTime updatedAt;
}
