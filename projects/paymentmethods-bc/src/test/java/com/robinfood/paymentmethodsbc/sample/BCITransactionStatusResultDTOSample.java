package com.robinfood.paymentmethodsbc.sample;

import java.math.BigDecimal;

import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;

/**
 * Obtiene objetos de prueba para test unitarios
 * @author Hernán A. Ramírez O.
 */
public class BCITransactionStatusResultDTOSample {

    public static BCITransactionStatusResponseDTO getBCITransactionStatusResultDTO(){
        return BCITransactionStatusResponseDTO
            .builder()
            .transaction(BCITransactionStatusResponseDTO.TransactionStatusResponse
                .builder()
                .success(true)
                .transactionStatus(1L)
                .accepted("status")
                .reason("reason")
                .event("capture")
                .date("2021-21-21")
                .transactionValue(BigDecimal.TEN)
                .transactionStatus(1L)
                .build())
            .build();
    }

    public static BCITransactionStatusResponseDTO getBCITransactionStatusResultDTOTransactionStatusNull(){
        return BCITransactionStatusResponseDTO
            .builder()
            .transaction(BCITransactionStatusResponseDTO.TransactionStatusResponse
                .builder()
                .success(true)
                .accepted("status")
                .reason("reason")
                .event("capture")
                .date("2021-21-21")
                .transactionValue(BigDecimal.ZERO)
                .transactionStatus(1L)
                .build())
            .build();
    }
}
