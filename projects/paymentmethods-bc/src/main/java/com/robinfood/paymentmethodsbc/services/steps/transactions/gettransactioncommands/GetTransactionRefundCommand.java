package com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;

public class GetTransactionRefundCommand implements GetTransactionCommandHandler {
    private TransactionsCommonOperations transactionsCommonOperations;
    private TransactionsConfig transactionsConfig;

    public GetTransactionRefundCommand() {
        // GetTransactionRefundCommand constructor
    }

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws PaymentStepException, EntityNotFoundException {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionsConfig = transactionsConfig;
        invokeForRefundPipeDTO((RefundPipeDTO) pipe);
    }

    private void invokeForRefundPipeDTO(
        RefundPipeDTO refundPipeDTO
    ) throws EntityNotFoundException, PaymentStepException {
        Long transactionId = refundPipeDTO.getOriginalTransactionId();
        Transaction originalTransaction = transactionsCommonOperations.getTransactionById(transactionId);
        if (
            !(
                originalTransaction.isSucceeded() &&
                    (
                        transactionsConfig.isStatusAccepted(originalTransaction.getTransactionStatus().getId())
                            || transactionsConfig.isStatusRefundRejected(
                                originalTransaction.getTransactionStatus().getId())
                    )
            )
        ) {
            throw new PaymentStepException(
                StepType.GENERATE_REFUND,
                "Solo se puede reembolsar transacciones en estado aceptado o reembolso rechazado."
            );
        }
        refundPipeDTO.setOriginalTransaction(originalTransaction);
        refundPipeDTO.setOriginalTransactionDetail(
            transactionsCommonOperations.getTransactionDetailByTransactionId(
                transactionId
            )
        );
    }
}
