package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PaymentMethodPaidPaymentEntity {

    private Double subtotal;

    private Double tax;

    private Double total;
}
