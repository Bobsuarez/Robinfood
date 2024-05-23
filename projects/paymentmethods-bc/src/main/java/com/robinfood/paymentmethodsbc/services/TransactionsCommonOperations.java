package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionLog;
import com.robinfood.paymentmethodsbc.model.TransactionStatus;
import com.robinfood.paymentmethodsbc.model.TransactionStatusLog;
import com.robinfood.paymentmethodsbc.model.TransactionUser;

public interface TransactionsCommonOperations {
    Transaction getTransactionById(Long id) throws EntityNotFoundException;

    Transaction getTransactionByUuid(String uuid)
        throws EntityNotFoundException;

    Transaction saveTransaction(Transaction transaction);

    TransactionDetail getTransactionDetailByTransactionId(Long transactionId)
        throws EntityNotFoundException;

    TransactionDetail getTransactionDetailByReference(
        String transactionReference
    )
        throws EntityNotFoundException;

    TransactionDetail getTransactionDetailByCode(String transactionCode)
        throws EntityNotFoundException;

    TransactionDetail saveTransactionDetail(
        TransactionDetail transactionDetail
    );

    TransactionStatusLog saveTransactionStatusLog(
        Transaction transaction,
        String comment,
        String transactionCode,
        String transactionReference,
        String authorizationCode
    );

    boolean canNotifyStatus(Transaction transaction);

    TransactionLog saveTransactionLog(
        Transaction transaction,
        String comment,
        String context
    );

    TransactionStatus getTransactionStatusById(Long id)
        throws EntityNotFoundException;


    TransactionUser saveUserTransaction(
        Transaction transaction,
        PaymentRequestDTO.UserDTO userTransactionDto
    );

    TransactionStatusLog getTransactionStatusLogByTransactionIdAndStatusId(
        Long transactionId,
        Long transactionStatusId
    )
        throws EntityNotFoundException;

    TransactionStatusLog getTransactionStatusLogByCodeAndStatusId(
        String transactionCode,
        Long transactionStatusId
    )
        throws EntityNotFoundException;

    TransactionStatusLog getTransactionStatusLogByReferenceAndStatusId(
        String transactionReference,
        Long transactionStatusId
    )
        throws EntityNotFoundException;
}
