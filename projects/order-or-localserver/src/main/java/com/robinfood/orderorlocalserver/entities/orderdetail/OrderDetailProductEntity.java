package com.robinfood.orderorlocalserver.entities.orderdetail;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class OrderDetailProductEntity {

    private final Long articleId;

    private final Long articleTypeId;

    private final Double basePrice;

    private final Long brandId;

    private final String brandName;

    private final Long categoryId;

    private final String categoryName;

    private final BigDecimal co2Total;

    private final BigDecimal deduction;

    private final BigDecimal discount;

    private final List<OrderDetailProductDiscountEntity> discounts;

    private final Long finalProductId;

    private final List<OrderDetailProductGroupEntity> groups;

    private final Long id;

    private final String image;

    private final Long brandMenuId;

    private final Long menuHallProductId;

    private final String name;

    private final Integer quantity;

    private final Long sizeId;

    private final String sizeName;

    private final String sku;

    private final List<OrderDetailProductTaxEntity> taxes;

    private final BigDecimal unitPrice;

    private final Double total;
}