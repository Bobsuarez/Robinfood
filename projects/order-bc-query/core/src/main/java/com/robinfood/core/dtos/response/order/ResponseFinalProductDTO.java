package com.robinfood.core.dtos.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ResponseFinalProductDTO {
    
    private Long articleId;

    private Long articleTypeId;

    private Long brandId;

    private Double basePrice;

    private String brandName;

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
