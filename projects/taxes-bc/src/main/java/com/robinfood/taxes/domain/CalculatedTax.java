package com.robinfood.taxes.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculatedTax {

    private Long id;

    private Long taxId;

    private Long taxTypeId;

    private String name;

    private BigDecimal taxRate;

    private BigDecimal value;

    private String sapId;

    private Long familyId;

    private Long familyTypeId;

}
