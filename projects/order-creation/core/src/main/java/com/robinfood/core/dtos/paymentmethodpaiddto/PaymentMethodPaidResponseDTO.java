package com.robinfood.core.dtos.paymentmethodpaiddto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
public class PaymentMethodPaidResponseDTO implements Serializable {

    private final String authorizarionCode;

    private final boolean generated;

    private final String message;

    private final String paymentGatewayName;

    private final String statusTraceName;
}
