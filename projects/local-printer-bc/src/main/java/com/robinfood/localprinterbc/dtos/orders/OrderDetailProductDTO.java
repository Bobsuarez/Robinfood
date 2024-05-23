package com.robinfood.localprinterbc.dtos.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.robinfood.localprinterbc.dtos.decorator.GroupPortionsPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.RemovePortionDTO;
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
    private String totalFullFormat;

    private GroupPortionsPrintDTO toAdd;

    private GroupPortionsPrintDTO toChange;

    private GroupPortionsPrintDTO toInclude;

    private RemovePortionDTO toRemove;

    private Boolean hasGroups;

    @Builder.Default
    private List<AdditionDTO> listAddition = new ArrayList<>();

    private Boolean hasAddition;

    public boolean hasReplacement() {
        return groups.stream().anyMatch(OrderDetailProductGroupDTO::hasReplacement);
    }

    public Boolean getHasGroups() {
        return groups != null && !groups.isEmpty();
    }

    public Boolean getHasAddition() {
        return listAddition != null && !listAddition.isEmpty();
    }
}

