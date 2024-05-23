package com.robinfood.paymentmethodsbc.services.steps.transactions.initialtransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.robinfood.paymentmethodsbc.configs.TransactionsConfig.STATUS_REFUND_PENDING_ID;
import static com.robinfood.paymentmethodsbc.configs.TransactionsConfig.STATUS_REFUND_REQUESTED_ID;

@Slf4j
public class InitialRefundCommand implements InitialTransactionCommandHandler {
    private TransactionsCommonOperations transactionsCommonOperations;
    private TransactionsConfig transactionsConfig;

    public InitialRefundCommand() {
        // InitialRefundCommand constructor
    }

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws EntityNotFoundException {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionsConfig = transactionsConfig;
        invokeForRefundPipeDTO((RefundPipeDTO) pipe);
    }

    public void invokeForRefundPipeDTO(
        RefundPipeDTO refundPipeDTO
    ) throws EntityNotFoundException {
        Transaction transaction = refundPipeDTO.getOriginalTransaction();

        Long statusId = transactionsConfig.getStatusId(STATUS_REFUND_REQUESTED_ID);
        String logActionComment = "requested";

        if (refundPipeDTO.isBciProcessRefund()) {
            statusId = transactionsConfig.getStatusId(STATUS_REFUND_PENDING_ID);
            logActionComment = "initiated";
        }

        TransactionStatus transactionStatus =
            transactionsCommonOperations.getTransactionStatusById(statusId);

        transaction.setTransactionStatus(transactionStatus);
        transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        transaction = transactionsCommonOperations.saveTransaction(transaction);

        log.info("Refund transaction status saved \n{}", transaction);

        transactionsCommonOperations.saveTransactionStatusLog(
            transaction,
            String.format("Refund %s, reason: %s", logActionComment, refundPipeDTO.getRefundReason()),
            null,
            null,
            null
        );
    }
}
