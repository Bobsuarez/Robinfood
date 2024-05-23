package com.robinfood.localserver.commons.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class OrderDetailDeductionDTO {

    private BigDecimal value;
}
