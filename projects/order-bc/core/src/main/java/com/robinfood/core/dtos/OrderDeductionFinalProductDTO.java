package com.robinfood.core.dtos;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class OrderDeductionFinalProductDTO {

    private Long id;

    private Long orderId;

    private Long productFinalId;

    private BigDecimal value;
}
