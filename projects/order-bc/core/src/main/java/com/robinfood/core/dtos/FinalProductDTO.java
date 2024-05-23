package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class FinalProductDTO {

    private FinalProductArticleDTO article;

    private OrderBrandDTO brand;

    private FinalProductCategoryDTO category;

    private Long companyId;

    private List<FinalProductDiscountDTO> discounts;

    private Long id;

    private String image;

    private String name;

    private Long orderId;

    private List<PortionDTO> portions;

    private Integer quantity;

    private List<RemovedPortionDTO> removedPortions;

    private FinalProductSizeDTO size;

    private Long storeId;

    private List<OrderProductTaxDTO> taxes;

    private BigDecimal co2Total;

    private Double value;
}
