package com.robinfood.core.entities;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ValidateTaxItemResponseEntity {

    private final Long familyId;

    private final Long id;

    private final String name;

    private final BigDecimal rate;

    private final String sapId;

    private final Long taxTypeId;

    private final Long taxId;

    private final BigDecimal value;
}
