package com.robinfood.core.entities.transactionrequestentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountEntity {

    private Long id;

    private Boolean isConsumptionDiscount;

    private Long orderFinalProductId;

    private Long typeId;

    private BigDecimal value;

    private String sku;
}
