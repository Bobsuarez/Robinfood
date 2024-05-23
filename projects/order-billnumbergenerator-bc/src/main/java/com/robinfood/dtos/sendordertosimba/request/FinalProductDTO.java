package com.robinfood.dtos.sendordertosimba.request;

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
public class FinalProductDTO {

    private FinalProductArticleDTO article;

    private FinalProductBrandDTO brand;

    private FinalProductCategoryDTO category;

    private BigDecimal co2Total = BigDecimal.ZERO;

    private List<FinalProductDiscountDTO> discounts;

    private List<DeductionDTO> deduction = new ArrayList<>();

    private List<GroupDTO> groups;

    private Long id;

    private String image;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    private List<RemovedPortionDTO> removedPortions;

    private FinalProductSizeDTO size;

    private String sku;

    private List<FinalProductTaxDTO> taxes;

    private BigDecimal totalPrice;

}
