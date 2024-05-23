package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetOrderDetailFinalProductDTO {

    private final Long articleId;

    private final Long articleTypeId;

    private final BigDecimal basePrice;

    private final Long brandId;

    private final String brandName;

    private final Long categoryId;

    private final String categoryName;

    private final Long id;

    private final String image;

    private final Long brandMenuId;

    private final String name;

    private final Integer quantity;

    private final BigDecimal unitPrice;

    private final Long sizeId;

    private final String sizeName;

    private final String sku;

    private final List<GetOrderDetailFinalProductGroupDTO> groups;

    private final BigDecimal discount;

    private final List<GetOrderDetailDiscountDTO> discounts;

    private final BigDecimal deduction;

    @JsonIgnore
    private final Long orderId;

    private final List<GetOrderDetailFinalProductTaxDTO> taxes;

    private final BigDecimal co2Total;

    private final Double total;

}

