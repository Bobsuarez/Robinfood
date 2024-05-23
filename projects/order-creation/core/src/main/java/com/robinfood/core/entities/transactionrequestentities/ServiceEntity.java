package com.robinfood.core.entities.transactionrequestentities;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceEntity {

    private BigDecimal discount;

    private Long id;

    private BigDecimal priceNt;

    private BigDecimal taxPercentage;

    private BigDecimal taxPrice;

    private BigDecimal total;

    private BigDecimal value;
}
