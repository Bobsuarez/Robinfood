package org.example.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class FinalProductTaxDTO {

    private Long articleId;

    private Long articleTypeId;

    private Long dicTaxId;

    private Long familyId;

    private Long familyTaxTypeId;

    private String taxTypeName;

    private BigDecimal taxPrice;

    private Long taxId;

    private Long taxTypeId;

    private BigDecimal taxValue;

}
