package com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;

public class GetTransactionPaymentCommand implements GetTransactionCommandHandler {
    private TransactionsCommonOperations transactionsCommonOperations;

    public GetTransactionPaymentCommand() {
        // GetTransactionPaymentCommand constructor
    }

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws PaymentStepException, EntityNotFoundException {
        this.transactionsCommonOperations = transactionsCommonOperations;
        invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);
    }

    private void invokeForTransactionPipeDTO(PaymentPipeDTO paymentPipeDTO)
        throws EntityNotFoundException {
        Long transactionId = paymentPipeDTO
            .getTransactionRequestDTO()
            .getTransactionId();
        TransactionLogger.invoke(paymentPipeDTO);
        paymentPipeDTO.setTransaction(
            transactionsCommonOperations.getTransactionById(transactionId)
        );
        paymentPipeDTO.setTransactionDetail(
            transactionsCommonOperations.getTransactionDetailByTransactionId(
                transactionId
            )
        );
        TransactionLogger.clear();
    }
}
