package com.robinfood.taxes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTaxDetail {

    private Long articleId;
    private Long productTypeId;
    private Long familyTaxTypeId;
    private Double value;
    private Long dicTaxId;
}
