package com.robinfood.paymentmethodsbc.services.steps.transactions.initialtransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.robinfood.paymentmethodsbc.configs.TransactionsConfig.STATUS_PENDING_ID;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.TRANSACTION_TYPE_PURCHASE;
import static com.robinfood.paymentmethodsbc.mappers.TransactionMapper.getTransactionDetailInitial;
import static com.robinfood.paymentmethodsbc.mappers.TransactionMapper.getTransactionFromCreateDTO;

@Slf4j
public class InitialPaymentCommand implements InitialTransactionCommandHandler {
    private TransactionsCommonOperations transactionsCommonOperations;
    private TransactionsConfig transactionsConfig;

    public InitialPaymentCommand() {
        // InitialPaymentCommand constructor
    }

    @Override
    public void invoke(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig,
        Object pipe
    ) throws EntityNotFoundException {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionsConfig = transactionsConfig;
        invokeForPaymentPipeDTO((PaymentPipeDTO) pipe);
    }

    private void invokeForPaymentPipeDTO(
        PaymentPipeDTO paymentPipeDTO
    ) throws EntityNotFoundException {
        Transaction transaction = saveInitialTransaction(paymentPipeDTO);
        TransactionDetail transactionDetail =
            saveInitialTransactionDetail(paymentPipeDTO, transaction);

        transactionsCommonOperations.saveUserTransaction(
            transaction, paymentPipeDTO.getTransactionRequestDTO().getUser()
        );

        paymentPipeDTO.setTransaction(transaction);
        paymentPipeDTO.setTransactionDetail(transactionDetail);

        paymentPipeDTO
            .getTransactionRequestDTO()
            .setTransactionId(transaction.getId());
        paymentPipeDTO
            .getTransactionRequestDTO()
            .setTransactionUuid(transaction.getUuid());
    }

    private Transaction saveInitialTransaction(
        PaymentPipeDTO transactionCreatePipeDTO
    ) throws EntityNotFoundException {
        Transaction transaction = getTransactionFromCreateDTO(
            transactionCreatePipeDTO
        );

        TransactionStatus transactionStatus = transactionsCommonOperations.getTransactionStatusById(
            transactionsConfig.getStatusId(STATUS_PENDING_ID)
        );

        transaction.setTransactionStatus(transactionStatus);
        transaction.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        transaction = transactionsCommonOperations.saveTransaction(transaction);

        log.info("Transaction saved \n{}", transaction);

        transactionsCommonOperations.saveTransactionStatusLog(
            transaction,
            "Transaction initiated",
            null,
            null,
            null
        );
        transactionCreatePipeDTO.setTransaction(transaction);
        return transaction;
    }

    private TransactionDetail saveInitialTransactionDetail(
        PaymentPipeDTO paymentPipeDTO,
        Transaction transaction
    ) {
        TransactionDetail transactionDetail =
            getTransactionDetailInitial(paymentPipeDTO, transaction);
        transactionDetail.setTransactionType(TRANSACTION_TYPE_PURCHASE);
        paymentPipeDTO.setTransactionDetail(transactionDetail);
        log.info("Transaction detail to be saved \n{}", transactionDetail);

        return transactionsCommonOperations.saveTransactionDetail(transactionDetail);
    }
}
