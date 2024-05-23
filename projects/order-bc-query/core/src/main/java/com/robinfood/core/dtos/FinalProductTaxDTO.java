package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class FinalProductTaxDTO {

    private Long articleId;

    private Long articleTypeId;

    private Long dicTaxId;

    private Long familyTaxTypeId;

    @Nullable
    private Long orderFinalProductId;

    private Long orderId;

    private Double taxPrice;

    private Double taxValue;
}
