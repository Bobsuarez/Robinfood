package com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;

public class GetTransactionJmsTransactionStatusCommand implements GetTransactionCommandHandler {
    private TransactionsCommonOperations transactionsCommonOperations;
    private TransactionsConfig transactionsConfig;

    public GetTransactionJmsTransactionStatusCommand() {
        // GetTransactionJmsTransactionStatusCommand constructor
    }

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws PaymentStepException, EntityNotFoundException {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionsConfig = transactionsConfig;
        invokeForJmsTransactionStatusPipeDTO((JmsTransactionStatusPipeDTO) pipe);
    }

    private void invokeForJmsTransactionStatusPipeDTO(
        JmsTransactionStatusPipeDTO jmsTransactionStatusPipeDTO
    ) throws PaymentStepException, EntityNotFoundException {
        TransactionData data = TransactionData.builder().build();
        data.getTransactionData(
            jmsTransactionStatusPipeDTO
                .getJmsUpdateTransactionStatusDTO()
                .getType(),
            jmsTransactionStatusPipeDTO
                .getJmsUpdateTransactionStatusDTO()
                .getKey(),
            jmsTransactionStatusPipeDTO
                .getJmsUpdateTransactionStatusDTO()
                .getIdentificator(),
            transactionsCommonOperations,
            transactionsConfig
        );
        jmsTransactionStatusPipeDTO.setTransactionDetail(
            data.getTransactionDetail()
        );
        jmsTransactionStatusPipeDTO.setTransaction(data.getTransaction());
    }
}
