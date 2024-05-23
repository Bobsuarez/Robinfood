package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;

public class TransactionCreationResponseDTOMock {

    public static TransactionCreationResponseDTO build() {
        return TransactionCreationResponseDTO.builder()
            .transaction(new TransactionResponseDTOMock().transactionCreationResponseDTO.getTransaction())
            .build();
    }

    public static TransactionCreationResponseDTO buildWithMultiplesOrders() {
        return TransactionCreationResponseDTO.builder()
            .transaction(
                new TransactionResponseDTOMock().transactionCreationResponseDTOWithMultiplesOrders
                    .getTransaction()
            )
            .build();
    }

    public static TransactionCreationResponseDTO buildTransactionIdZero() {
        return TransactionCreationResponseDTO.builder()
                .transaction(new TransactionResponseDTOMock().transactionCreationResponseTransactionIdZeroDTO.getTransaction())
                .build();
    }

}
