package com.robinfood.paymentmethodsbc.services.steps.transactions.gettransactioncommands;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionStatusLog;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.robinfood.paymentmethodsbc.configs.TransactionsConfig.STATUS_REFUND_PENDING_ID;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.STATUS_KEY_CODE;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.STATUS_KEY_ID;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.STATUS_KEY_REFERENCE;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.STATUS_KEY_UUID;
import static com.robinfood.paymentmethodsbc.constants.AppConstants.TRANSACTION_TYPE_REFUND;

@Getter
@Builder
public class TransactionData {
    private Transaction transaction;
    private TransactionDetail transactionDetail;

    public TransactionData(
        Transaction transaction,
        TransactionDetail transactionDetail
    ) {
        this.transaction = transaction;
        this.transactionDetail = transactionDetail;
    }

    private void getTransactionDataById(
        Long transactionId,
        TransactionsCommonOperations transactionsCommonOperations
    ) throws EntityNotFoundException, PaymentStepException {
        if (Objects.isNull(transactionId)) {
            throw new PaymentStepException(
                StepType.GET_TRANSACTION,
                "'identificator' must be an integer value"
            );
        }
        transaction = transactionsCommonOperations.getTransactionById(transactionId);
        transactionDetail = transaction.getTransactionDetail();
    }

    private void getTransactionDataByUuid(
        String transactionUuid,
        TransactionsCommonOperations transactionsCommonOperations
    ) throws EntityNotFoundException {
        transaction = transactionsCommonOperations.getTransactionByUuid(transactionUuid);
        transactionDetail = transaction.getTransactionDetail();
    }

    private void getTransactionDataByTransactionCode(
        String type,
        String transactionCode,
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig
    ) throws EntityNotFoundException {
        if (TRANSACTION_TYPE_REFUND.equalsIgnoreCase(type)) {
            TransactionStatusLog trxLog =
                transactionsCommonOperations.getTransactionStatusLogByCodeAndStatusId(
                    transactionCode, transactionsConfig.getStatusId(STATUS_REFUND_PENDING_ID)
                );
            transaction = trxLog.getTransaction();
            transactionDetail = trxLog.getTransaction().getTransactionDetail();
            return;
        }
        transactionDetail = transactionsCommonOperations.getTransactionDetailByCode(transactionCode);
        transaction = transactionDetail.getTransaction();
    }

    private void getTransactionDataByTransactionReference(
        String type,
        String transactionReference,
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig
    ) throws EntityNotFoundException {
        if (TRANSACTION_TYPE_REFUND.equalsIgnoreCase(type)) {
            TransactionStatusLog trxLog =
                transactionsCommonOperations.getTransactionStatusLogByReferenceAndStatusId(
                    transactionReference, transactionsConfig.getStatusId(STATUS_REFUND_PENDING_ID)
                );
            transaction = trxLog.getTransaction();
            transactionDetail = trxLog.getTransaction().getTransactionDetail();
            return;
        }
        transactionDetail = transactionsCommonOperations.getTransactionDetailByReference(transactionReference);
        transaction = transactionDetail.getTransaction();
    }

    public void getTransactionData(
        String type,
        String key,
        String identificator,
        TransactionsCommonOperations transactionsCommonOperations,
        TransactionsConfig transactionsConfig
    ) throws PaymentStepException, EntityNotFoundException {
        List<String> validKeys = List.of(
            STATUS_KEY_ID,
            STATUS_KEY_UUID,
            STATUS_KEY_CODE,
            STATUS_KEY_REFERENCE
        );

        switch (key.toLowerCase(Locale.getDefault())) {
            case STATUS_KEY_ID:
                getTransactionDataById(Utilities.stringToLong(identificator), transactionsCommonOperations);
                break;
            case STATUS_KEY_UUID:
                getTransactionDataByUuid(identificator, transactionsCommonOperations);
                break;
            case STATUS_KEY_CODE:
                getTransactionDataByTransactionCode(
                    type, identificator, transactionsCommonOperations, transactionsConfig
                );
                break;
            case STATUS_KEY_REFERENCE:
                getTransactionDataByTransactionReference(
                    type, identificator, transactionsCommonOperations, transactionsConfig
                );
                break;
            default:
                throw new PaymentStepException(
                    StepType.GET_TRANSACTION,
                    String.format("key must be one of the following values: %s", validKeys)
                );
        }
    }
}
