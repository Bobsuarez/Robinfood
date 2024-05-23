package com.robinfood.core.dtos.request.order;


import com.robinfood.core.dtos.DeductionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class FinalProductDTO {

    @NotNull
    @Valid
    private FinalProductArticleDTO article;

    @NotNull
    @Valid
    private BrandDTO brand;

    private FinalProductCategoryDTO category;

    private List<DeductionDTO> deduction;

    private Long companyId;

    @NotNull
    @Size
    @Valid
    private List<FinalProductDiscountDTO> discounts;

    private Double discountPrice;

    private Long id;

    private Long orderId;

    private String image;

    private String name;

    private List<FinalProductPortionDTO> portions;

    private Integer quantity;

    private List<FinalProductRemovedPortionDTO> removedPortions;

    private FinalProductSizeDTO size;

    private String sku;

    private Long storeId;

    private List<FinalProductTaxDTO> taxes;

    private BigDecimal co2Total;

    private BigDecimal value;

    public BigDecimal getTotalDiscounts() {
         double totalDiscount = discounts.stream()
            .mapToDouble(FinalProductDiscountDTO::getValue)
            .sum();

         return BigDecimal.valueOf(totalDiscount);
    }

}
