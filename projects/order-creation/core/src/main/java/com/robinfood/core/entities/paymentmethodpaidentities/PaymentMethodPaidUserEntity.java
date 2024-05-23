package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PaymentMethodPaidUserEntity {

    private String firstName;

    private String lastName;

    private String phoneCode;

    private String phoneNumber;

    private Long userId;
}
