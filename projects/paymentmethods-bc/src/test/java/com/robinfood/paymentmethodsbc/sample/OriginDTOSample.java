package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.OriginDTO;

public final class OriginDTOSample {

    private OriginDTOSample() {}

    public static OriginDTO getOriginDTO() {
        return OriginDTO.builder()
            .channelId(4L)
            .storeId(5L)
            .ipAddress("10.14.1.71")
            .build();
    }
}
