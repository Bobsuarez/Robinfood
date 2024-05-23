package com.robinfood.core.entities.transactionrequestentities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {

    private PaymentMethodDetailEntity detail;

    private Long id;

    private Long originId;

    private BigDecimal value;
}
