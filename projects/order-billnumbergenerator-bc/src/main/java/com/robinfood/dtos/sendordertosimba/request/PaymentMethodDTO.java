package com.robinfood.dtos.sendordertosimba.request;

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

    private PaymentMethodDetailDTO detail;

    private Long originId;

    private BigDecimal value;
}
