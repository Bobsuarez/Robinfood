package com.robinfood.core.entities.transactionrequestentities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalProductTaxesEntity {

    private Long articleId;

    private Long articleTypeId;

    private Long dicTaxId;

    private Long familyId;

    private Long familyTaxTypeId;

    private Long taxId;

    private BigDecimal taxPrice;

    private Long taxTypeId;

    private String taxTypeName;

    private BigDecimal taxValue;

}
