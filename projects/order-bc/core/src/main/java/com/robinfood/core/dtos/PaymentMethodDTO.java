package com.robinfood.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentMethodDTO {

    private OrderPaymentDetailDTO detail;

    private Long id;

    private Long originId;

    private Long paymentMethodId;

    private Long transactionId;

    private Double value;

}
