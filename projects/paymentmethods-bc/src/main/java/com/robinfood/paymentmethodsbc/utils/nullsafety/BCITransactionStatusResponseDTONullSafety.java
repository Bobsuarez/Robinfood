package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO.TransactionStatusResponse;

import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

public final class BCITransactionStatusResponseDTONullSafety {

    private BCITransactionStatusResponseDTONullSafety() {}

    public static BCITransactionStatusResponseDTO validateBCITransactionStatusResponseDTO(
        BCITransactionStatusResponseDTO bciTransactionStatusResponseDTO
    ) {
        return requireNonNullElse(bciTransactionStatusResponseDTO, BCITransactionStatusResponseDTO.builder().build());
    }

    public static TransactionStatusResponse validateTransactionStatusResponse(
        TransactionStatusResponse transactionStatusResponse
    ) {
        return Optional.ofNullable(transactionStatusResponse)
            .orElse(TransactionStatusResponse.builder().build());
    }
}
