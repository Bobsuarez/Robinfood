package com.robinfood.dtos.request.orderbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ServiceDTO {

    private BigDecimal discount;

    private Long id;

    private BigDecimal priceNt;

    private BigDecimal taxPercentage;

    private BigDecimal taxPrice;

    private BigDecimal total;

    private BigDecimal value;

}
