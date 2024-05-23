package com.robinfood.core.mappers;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionResponseDTO;

import java.util.List;

public final class TransactionResponseMappers {

    private TransactionResponseMappers() {
        // Constructor empty
    }

    public static TransactionResponseDTO transactionRequestDTOtoResponse(
            TransactionRequestDTO transactionRequestDTO,
            List<OrderResponseDTO> orderResponseDTOS
    ) {

        return TransactionResponseDTO.builder()
                .createdAt(transactionRequestDTO.getOrderCreatedAt())
                .id(transactionRequestDTO.getId())
                .orders(orderResponseDTOS)
                .uuid(transactionRequestDTO.getUuid().toString())
                .build();
    }
}
