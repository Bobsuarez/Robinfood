package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodPaidResponseEntity {

    private PaymentMethodPaidReferenceEntity entity;

    private Boolean generated;

    private Long id;

    private PaymentMethodPaidPaymentEntity payment;

    private Integer platformTypeId;

    private PaymentMethodPaidPlatformTypeInfoEntity platformTypeInfo;

    private PaymentMethodPaidStatusEntity status;

    private String uuid;
}
