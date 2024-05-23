package com.robinfood.localserver.commons.entities.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailProductEntity {

    private Long articleId;

    private Long articleTypeId;

    private Double basePrice;

    private Long brandId;

    private String brandName;

    private Long categoryId;

    private String categoryName;

    private BigDecimal co2Total;

    private BigDecimal deduction;

    private BigDecimal discount;

    private List<OrderDetailProductDiscountEntity> discounts;

    private Long finalProductId;

    private List<OrderDetailProductGroupEntity> groups;

    private Long id;

    private String image;

    private Long brandMenuId;

    private Long menuHallProductId;

    private String name;

    private Integer quantity;

    private Long sizeId;

    private String sizeName;

    private String sku;

    private List<OrderDetailProductTaxEntity> taxes;

    private Double unitPrice;

    private Double total;
}
