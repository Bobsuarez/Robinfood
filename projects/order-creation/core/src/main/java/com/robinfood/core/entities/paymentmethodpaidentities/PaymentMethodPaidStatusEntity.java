package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodPaidStatusEntity {

    private Long id;

    private String name;

}
