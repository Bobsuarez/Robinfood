package com.robinfood.localserver.commons.dtos.orders.billing;

import com.robinfood.localserver.commons.dtos.orders.OrderDetailProductDiscountDTO;
import com.robinfood.localserver.commons.dtos.orders.OrderDetailProductGroupDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderBillingProductDTO {

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

    @Builder.Default
    private List<OrderDetailProductDiscountDTO> discounts = new ArrayList<>();

    @Builder.Default
    private List<OrderDetailProductGroupDTO> groups = new ArrayList<>();

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

    private TreasuryCategoryDTO treasuryCategory;

    private List<OrderBillingTaxesDTO> taxes;

    private Double unitPrice;

    private Double total;

}
