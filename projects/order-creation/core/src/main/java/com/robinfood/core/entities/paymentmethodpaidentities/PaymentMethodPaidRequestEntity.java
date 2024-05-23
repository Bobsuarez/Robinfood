package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PaymentMethodPaidRequestEntity {

    private Long countryId;

    private PaymentMethodPaidReferenceEntity entity;

    private PaymentMethodPaidOriginEntity origin;

    private PaymentMethodPaidPaymentEntity payment;

    private int platformTypeId;

    private PaymentMethodEntity paymentMethod;

    private PaymentMethodPaidUserEntity user;
}
