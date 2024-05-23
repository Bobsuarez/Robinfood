package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.TransactionStatus;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class TransactionStatusSample {

    public static TransactionStatus getTransactionStatus() {
        return TransactionStatus
            .builder()
            .id(1L)
            .name("Status name")
            .description("Description")
            .build();
    }
}
