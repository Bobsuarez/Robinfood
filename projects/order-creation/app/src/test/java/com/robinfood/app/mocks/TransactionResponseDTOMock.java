package com.robinfood.app.mocks;

import com.robinfood.core.dtos.transactionresponsedto.OrderResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionResponseDTO;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TransactionResponseDTOMock {

    private final Long TRANSACTION_ID = 1L;
    private final Long TRANSACTION_ZERO_ID = 0L;
    private final String UUID = "1234";

    private final List<OrderResponseDTO> orderResponseDTOS = Collections.singletonList(OrderResponseDTOMocks.build());

    private final TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(
            TRANSACTION_ID,
            "",
            orderResponseDTOS,
            UUID
    );

    private final TransactionResponseDTO transactionResponseTransactionIdZeroDTO = new TransactionResponseDTO(
            TRANSACTION_ZERO_ID,
            "",
            orderResponseDTOS,
            UUID
    );

    public final TransactionCreationResponseDTO transactionCreationResponseDTO = new TransactionCreationResponseDTO(
            transactionResponseDTO
    );

    public final TransactionCreationResponseDTO transactionCreationResponseDTOWithMultiplesOrders = new TransactionCreationResponseDTO(
        TransactionResponseDTO.builder()
            .id(TRANSACTION_ID)
            .orders(Arrays.asList(
                OrderResponseDTOMocks.build(),
                OrderResponseDTOMocks.build()
            ))
            .uuid(UUID)
            .build()
    );

    public final TransactionCreationResponseDTO transactionCreationResponseTransactionIdZeroDTO = new TransactionCreationResponseDTO(
            transactionResponseTransactionIdZeroDTO
    );
}
