package com.robinfood.localserver.commons.entities.orders.billing;

import com.robinfood.localserver.commons.entities.orders.OrderDetailProductDiscountEntity;
import com.robinfood.localserver.commons.entities.orders.OrderDetailProductGroupEntity;
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
public class OrderBillingProductEntity {

    private Long articleId;

    private String articleName;

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

    private List<OrderDetailProductGroupEntity> groups;

    private Long id;

    private String image;

    private Long brandMenuId;

    private Long menuHallProductId;

    private String name;

    private String ncm;

    private Integer quantity;

    private Long sizeId;

    private String sizeName;

    private String sku;

    private TreasuryCategoryEntity treasuryCategory;

    private List<OrderBillingTaxesEntity> taxes;

    private Double unitPrice;

    private Double total;

}
