package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;

public final class JmsEntityRefundRequestDTOSample {

    private JmsEntityRefundRequestDTOSample() {}

    public static JmsEntityRefundRequestDTO getJmsEntityRefundRequestDTO() {
        return JmsEntityRefundRequestDTO.builder()
            .entityId(1L)
            .entitySourceId(1L)
            .entitySourceReference("uuid")
            .reason("refund")
            .build();
    }
}
