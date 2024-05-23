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
public class PaymentMethodPaidRequestDTO implements Serializable {

    private Long countryId;

    private PaymentMethodPaidReferenceDTO entity;

    private PaymentMethodPaidOriginDTO origin;

    private PaymentMethodPaidPaymentDTO payment;

    private Long platformId;

    private int platformTypeId;

    private PaymentMethodPaidPlatformTypeInfoDTO paymentMethod;

    private PaymentMethodPaidUserDTO user;
}
