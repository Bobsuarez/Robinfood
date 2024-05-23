package com.robinfood.core.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ValidateTaxResponseDTO  implements Serializable {

    private final Long articleId;

    private final Long articleTypeId;

    private final BigDecimal discount;

    private final BigDecimal price;

    private final Integer quantity;

    private final List<ValidateTaxItemResponseDTO> taxes;

    private final BigDecimal totalTax;
}
