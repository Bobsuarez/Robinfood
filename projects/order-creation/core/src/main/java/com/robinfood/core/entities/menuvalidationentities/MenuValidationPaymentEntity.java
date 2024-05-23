package com.robinfood.core.entities.menuvalidationentities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuValidationPaymentEntity {

    private BigDecimal discount;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private Integer taxType;

    private BigDecimal total;
}
