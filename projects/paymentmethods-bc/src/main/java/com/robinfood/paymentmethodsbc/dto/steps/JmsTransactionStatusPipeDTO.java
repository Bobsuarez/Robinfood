package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JmsTransactionStatusPipeDTO {
    private JmsUpdateTransactionStatusDTO jmsUpdateTransactionStatusDTO;

    private String jmsUpdateTransactionStatusJsonMessage;

    private Transaction transaction;

    private TransactionDetail transactionDetail;

    private SettingsDTO settings;

    private boolean notifyStatus;
}
