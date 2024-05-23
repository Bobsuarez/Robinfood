package com.robinfood.core.entities.paymentmethodpaidentities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PaymentMethodPaidOriginEntity {

    private Long channelId;

    private Long platformId;

    private Long storeId;

    private String ipAddress;
}
