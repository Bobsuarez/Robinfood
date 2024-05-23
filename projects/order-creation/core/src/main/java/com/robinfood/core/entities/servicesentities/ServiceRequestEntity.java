package com.robinfood.core.entities.servicesentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ServiceRequestEntity {

    private BigDecimal discount;

    private BigDecimal grossValue;

    private BigDecimal netValue;

    private Long storeId;
}
