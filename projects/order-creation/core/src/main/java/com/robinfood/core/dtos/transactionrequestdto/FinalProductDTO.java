package com.robinfood.core.dtos.transactionrequestdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.robinfood.core.dtos.DeductionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalProductDTO implements Serializable {

    private static final long serialVersionUID = 7650630725663371671L;

    @NotNull
    @Valid
    private FinalProductArticleDTO article;

    @NotNull
    @Valid
    private FinalProductBrandDTO brand;

    private FinalProductCategoryDTO category;

    @NotNull
    @Size
    @Valid
    private List<FinalProductDiscountDTO> discounts;

    @Builder.Default
    private List<DeductionDTO> deduction = new ArrayList<>();

    @NotNull
    @Size
    @Valid
    private List<GroupDTO> groups;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String image;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Size
    @Valid
    private List<RemovedPortionDTO> removedPortions;

    @NotNull
    @Valid
    private FinalProductSizeDTO size;

    private String sku;

    @Size
    @Valid
    private List<FinalProductTaxDTO> taxes;

    @NotNull
    @Positive
    private BigDecimal totalPrice;

    @Builder.Default
    private BigDecimal co2Total = BigDecimal.ZERO;

    /**
     * Multiplies the value of the final product by its quantity
     *
     * @return the total value of a final product
     */

    @JsonIgnore
    public BigDecimal getTotalDiscounts() {

        BigDecimal totalDiscount = discounts
                .stream()
                .filter(isProduct -> isProduct.getIsProductDiscount().equals(Boolean.TRUE))
                .map(FinalProductDiscountDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        totalDiscount = totalDiscount.multiply(new BigDecimal(quantity));

        return totalPrice.subtract(totalDiscount);

    }
}
