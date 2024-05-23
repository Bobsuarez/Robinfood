package com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;

public class GetTransactionTransactionStatusCommand implements GetTransactionCommandHandler {
    private TransactionsCommonOperations transactionsCommonOperations;
    private TransactionsConfig transactionsConfig;

    public GetTransactionTransactionStatusCommand() {
        // GetTransactionTransactionStatusCommand constructor
    }

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws PaymentStepException, EntityNotFoundException {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionsConfig = transactionsConfig;
        invokeForTransactionStatusPipeDTO((TransactionStatusPipeDTO) pipe);
    }

    private void invokeForTransactionStatusPipeDTO(
        TransactionStatusPipeDTO transactionStatusPipeDTO
    ) throws PaymentStepException, EntityNotFoundException {
        TransactionData data = TransactionData.builder().build();
        data.getTransactionData(
            transactionStatusPipeDTO
                .getTransactionStatusUpdateRequestDTO()
                .getType(),
            transactionStatusPipeDTO
                .getTransactionStatusUpdateRequestDTO()
                .getKey(),
            transactionStatusPipeDTO
                .getTransactionStatusUpdateRequestDTO()
                .getIdentificator(),
            transactionsCommonOperations,
            transactionsConfig
        );
        transactionStatusPipeDTO.setTransactionDetail(data.getTransactionDetail());
        transactionStatusPipeDTO.setTransaction(data.getTransaction());
        TransactionLogger.invoke(data.getTransaction());
    }
}
