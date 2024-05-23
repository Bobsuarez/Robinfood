package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ValidateTaxItemResponseDTO  implements Serializable {

    private final Long familyId;

    private final Long id;

    private final BigDecimal rate;

    private final String sapId;

    private final Long taxId;

    private final Long taxTypeId;

    private final String taxTypeName;

    private final BigDecimal value;


}
