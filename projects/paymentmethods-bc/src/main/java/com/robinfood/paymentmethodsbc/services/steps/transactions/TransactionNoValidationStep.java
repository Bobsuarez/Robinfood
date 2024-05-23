package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.TransactionMapper;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implementación de PaymentNoValidation.
 * Genera la transacción sin validaciones
 */
@Slf4j
@Component
public class TransactionNoValidationStep implements StepActions {
    private final TransactionsCommonOperations transactionsCommonOperations;
    private final TransactionsConfig transactionsConfig;

    public TransactionNoValidationStep(
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig
    ) {
        this.transactionsCommonOperations = transactionsCommonOperations;
        this.transactionsConfig = transactionsConfig;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            invokeForTransactionPipeDTO((PaymentPipeDTO) pipe);
            return;
        }

        throw new PaymentStepException(
            StepType.GENERATE_PAYMENT_NO_VALIDATION,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    // Funciones invoke

    private void invokeForTransactionPipeDTO(PaymentPipeDTO transactionPipeDTO)
        throws EntityNotFoundException {
        Transaction transaction = saveTransactionNoValidation(transactionPipeDTO);
        TransactionDetail transactionDetail = saveTransactionDetailNoValidation(
            transactionPipeDTO,
            transaction
        );

        transactionsCommonOperations.saveUserTransaction(
            transaction,
            transactionPipeDTO.getTransactionRequestDTO().getUser()
        );

        transactionPipeDTO.setTransaction(transaction);
        transactionPipeDTO.setTransactionDetail(transactionDetail);

        transactionPipeDTO
            .getTransactionRequestDTO()
            .setTransactionId(transaction.getId());
        transactionPipeDTO
            .getTransactionRequestDTO()
            .setTransactionUuid(transaction.getUuid());
    }

    public Transaction saveTransactionNoValidation(
        PaymentPipeDTO transactionCreatePipeDTO
    )
        throws EntityNotFoundException {
        Transaction transaction = TransactionMapper.getTransactionFromCreateDTO(
            transactionCreatePipeDTO
        );

        TransactionStatus transactionStatus = transactionsCommonOperations.getTransactionStatusById(
            transactionsConfig.getStatusId(TransactionsConfig.STATUS_ACCEPTED_ID)
        );

        boolean isSucceded = true;
        transaction.setSucceeded(isSucceded);
        transaction.setTransactionStatus(transactionStatus);
        transaction.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        transaction.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        transaction = transactionsCommonOperations.saveTransaction(transaction);

        log.info("Transaction saved \n{}", transaction);

        transactionsCommonOperations.saveTransactionStatusLog(
            transaction,
            "Transaction without validation saved successfully",
            null,
            null,
            null
        );
        transactionsCommonOperations.saveTransactionLog(
            transaction,
            "Transaction without validation saved successfully",
            transaction.getPaymentMethod().getName()
        );

        transactionCreatePipeDTO.setTransaction(transaction);
        return transaction;
    }

    public TransactionDetail saveTransactionDetailNoValidation(
        PaymentPipeDTO transactionDetailDTO,
        Transaction transaction
    ) {
        TransactionDetail transactionDetail = TransactionMapper.getTransactionDetailInitial(
            transactionDetailDTO,
            transaction
        );
        transactionDetail.setTransactionType(
            AppConstants.TRANSACTION_TYPE_PURCHASE
        );
        transactionDetailDTO.setTransactionDetail(transactionDetail);

        return transactionsCommonOperations.saveTransactionDetail(
            transactionDetail
        );
    }
}
