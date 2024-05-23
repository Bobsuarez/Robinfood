package com.robinfood.core.dtos.orderbyuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponsePaymentDiscountDTO {

    private Long id;

    private Double value;

}
