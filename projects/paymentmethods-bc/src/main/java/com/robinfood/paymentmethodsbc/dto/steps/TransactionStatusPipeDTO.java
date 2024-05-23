package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionStatusUpdateRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatusLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusPipeDTO {
    private TransactionStatusUpdateRequestDTO transactionStatusUpdateRequestDTO;
    private BCITransactionStatusResponseDTO transactionStatusResultDTO;
    private Transaction transaction;
    private TransactionDetail transactionDetail;
    private TransactionStatusLog transactionStatusLog;
    private SettingsDTO settings;
    private boolean notifyStatus;

    public TransactionStatusPipeDTO(
        TransactionStatusUpdateRequestDTO transactionStatusUpdateRequestDTO
    ) {
        this.transactionStatusUpdateRequestDTO = transactionStatusUpdateRequestDTO;
    }
}
