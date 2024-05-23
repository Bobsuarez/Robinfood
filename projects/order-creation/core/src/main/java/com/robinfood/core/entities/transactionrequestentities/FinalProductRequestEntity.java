package com.robinfood.core.entities.transactionrequestentities;

import java.math.BigDecimal;
import java.util.List;

import com.robinfood.core.dtos.DeductionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalProductRequestEntity {

    private FinalProductArticleEntity article;

    private BrandEntity brand;

    private FinalProductCategoryEntity category;

    private List<DeductionDTO> deduction;

    private List<DiscountEntity> discounts;

    private Long id;

    private String image;

    private String name;

    private List<PortionRequestEntity> portions;

    private Integer quantity;

    private List<RemovedPortionEntity> removedPortions;

    private FinalProductSizeEntity size;

    private String sku;

    private List<FinalProductTaxesEntity> taxes;

    private BigDecimal value;

    private BigDecimal co2Total;
}
