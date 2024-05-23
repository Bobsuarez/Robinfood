package com.robinfood.core.entities.transactionresponseentities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDiscountResponseEntity {

    private Long externalDiscount;

    private Long finalProductId;

    private Long id;

    private BigDecimal value;
}
