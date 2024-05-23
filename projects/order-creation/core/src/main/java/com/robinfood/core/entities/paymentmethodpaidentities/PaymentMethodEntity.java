package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentMethodEntity {

    private Long creditCardId;

    private Long id;

    private Long installmentsNumber;

    private List<ReferencePaymentMethodsEntity> referencePaymentMethods;

    private String terminalUuid;
}
