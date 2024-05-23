package com.robinfood.app.mocks;

import com.robinfood.core.dtos.ConfigTransactionResponseDTO;

public class ConfigTransactionResponseDTOMock {

    public static ConfigTransactionResponseDTO build() {
        return ConfigTransactionResponseDTO.builder()
                .transactionCreationResponse(TransactionCreationResponseDTOMock.build())
                .build();
    }

    public static ConfigTransactionResponseDTO withOrderIds() {
        return ConfigTransactionResponseDTO.builder()
                .transactionCreationResponse(TransactionCreationResponseDTOMock.buildWithMultiplesOrders())
                .build();
    }
}
