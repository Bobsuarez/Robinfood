package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PaymentMethodPaidReferenceEntity {

    private Long id;

    private String reference;

    private Integer source;
}
