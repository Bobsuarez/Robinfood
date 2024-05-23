package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionGenerateDTO;

public class JmsTransactionGenerateDTOSample {

    public static JmsTransactionGenerateDTO getJmsTransactionGenerateDTO() {
        return JmsTransactionGenerateDTO.builder()
            .transaction(PaymentRequestDTO.builder().build())
            .retries(1L)
            .build();
    }
}
