package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.TransactionUser;

/**
 * Obtiene objetos de prueba para test unitarios
 * @author Wilson Guerrero <wguerrero@robinfood.com>
 */
public class TransactionUserSample {

    public static TransactionUser getTransactionUser() {
        return TransactionUser
            .builder()
            .transactionId(TransactionSamples.getTransaction().getId())
            .userId(TransactionSamples.getTransaction().getUserId())
            .firstName("Jhon")
            .lastName("Doe")
            .phoneCode("57")
            .phoneNumber("321321321")
            .build();
    }
}
