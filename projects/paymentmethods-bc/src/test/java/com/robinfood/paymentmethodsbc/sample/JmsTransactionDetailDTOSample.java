package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class JmsTransactionDetailDTOSample {

    public static JmsTransactionDetailDTO getJmsTransactionDetailDTO() {
        return JmsTransactionDetailDTO
            .builder()
            .status(1L)
            .paymentGatewayId(1L)
            .countryId(1L)
            .entityId(1L)
            .build();
    }
}
