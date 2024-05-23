package com.robinfood.core.dtos.paymentmethodpaiddto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentMethodPaidUserDTO implements Serializable {

    private String firstName;

    private String lastName;

    private String phoneCode;

    private String phoneNumber;

    private Long userId;
}
