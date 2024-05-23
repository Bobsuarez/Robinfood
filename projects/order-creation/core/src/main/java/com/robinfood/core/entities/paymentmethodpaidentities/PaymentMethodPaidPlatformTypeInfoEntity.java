package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentMethodPaidPlatformTypeInfoEntity {

    private Long paymentGatewayId;

    private String paymentGatewayName;

}
