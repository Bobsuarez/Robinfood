package com.robinfood.core.dtos.paymentmethodpaiddto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentMethodPaidPlatformTypeInfoDTO implements Serializable {

    private Long creditCardId;

    private Long id;

    private Long installmentsNumber;

    private List<ReferencePaymentMethodsDTO> referencePaymentMethods;

    private String terminalUuid;
}
