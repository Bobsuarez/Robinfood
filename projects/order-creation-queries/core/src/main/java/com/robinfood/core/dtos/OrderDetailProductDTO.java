package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailProductDTO {

    private Long articleId;

    private String articleName;

    private Long articleTypeId;

    private final Double basePrice;

    private Long brandId;

    private final String brandName;

    private final Long categoryId;

    private final String categoryName;

    private final BigDecimal co2Total;

    private final BigDecimal deduction;

    private Long displayType;

    private final BigDecimal discount;

    private final List<OrderDetailProductDiscountDTO> discounts;

    private final Long finalProductId;

    private final List<OrderDetailProductGroupDTO> groups;

    private final Long id;

    private String image;

    private Long brandMenuId;

    private Long menuHallProductId;

    private final String name;

    private final Integer quantity;

    private final Long sizeId;

    private final String sizeName;

    private String sku;

    private final List<OrderDetailProductTaxDTO> taxes;

    private final BigDecimal unitPrice;

    private final Double total;

    public boolean hasReplacement() {
        return groups.stream().anyMatch(OrderDetailProductGroupDTO::hasReplacement);
    }
}
