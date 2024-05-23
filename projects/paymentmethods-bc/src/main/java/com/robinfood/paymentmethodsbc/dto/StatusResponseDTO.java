package com.robinfood.paymentmethodsbc.dto;

import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StatusResponseDTO {
    private BCITransactionStatusResponseDTO statusResult;

    private Integer statusCode;

    private String errorMessage;
}
