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
public class PaymentMethodDTO {

    private Long id;

    private Long originId;

    private PaymentMethodDetailDTO detail;

    private BigDecimal value;
}
