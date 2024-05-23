package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class OrderPaymentDTO {

    private OrderPaymentDetailDTO detail;

    private Double discount;

    private Long id;

    private Long orderId;

    private Long originId;

    private Long paymentMethodId;

    private Double subtotal;

    private Double tax;

    private Double value;
}
