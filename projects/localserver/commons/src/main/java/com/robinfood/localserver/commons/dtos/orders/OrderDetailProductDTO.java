package com.robinfood.localserver.commons.dtos.orders;

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
public class OrderDetailProductDTO {

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

    private List<OrderDetailProductDiscountDTO> discounts;

    private Long ProductId;

    private List<OrderDetailProductGroupDTO> groups;

    private Long id;

    private String image;

    private Long brandMenuId;

    private Long menuHallProductId;

    private String name;

    private Integer quantity;

    private Long sizeId;

    private String sizeName;

    private String sku;

    private List<OrderDetailProductTaxDTO> taxes;

    private Double unitPrice;

    private Double total;

    public boolean hasReplacement() {
        return groups.stream().anyMatch(OrderDetailProductGroupDTO::hasReplacement);
    }
}
