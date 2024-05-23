package com.robinfood.paymentmethodsbc.utils;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO.EntityDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.model.Transaction;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;

@Slf4j
public final class TransactionLogger {
    public static final String UUID_KEY = "uuid";
    public static final String EVENT_KEY = "eventId";
    public static final String EVENT_MESSAGE_KEY = "eventMessage";

    private TransactionLogger() {}

    public static void clear() {
        ThreadContext.clearAll();

    }

    public static void invoke(PaymentRequestDTO transactionDTO) {
        ThreadContext.put(
            UUID_KEY,
            Optional
                .ofNullable(getUuid(transactionDTO))
                .orElse(StringUtils.EMPTY)
        );
    }

    public static void invoke(
        BCITransactionStatusRequestDTO bciTransactionStatusRequestDTO
    ) {
        ThreadContext.put(
            UUID_KEY,
            Optional
                .ofNullable(
                    bciTransactionStatusRequestDTO.getTransaction().getUuid()
                )
                .orElse(StringUtils.EMPTY)
        );
    }

    public static void invoke(JmsUpdateTransactionStatusDTO updateDTO) {
        ThreadContext.put(
            UUID_KEY,
            Optional.ofNullable(updateDTO.getUuid()).orElse(StringUtils.EMPTY)
        );
    }

    public static void invoke(Transaction transaction) {
        Optional
            .ofNullable(ThreadContext.get(UUID_KEY))
            .ifPresentOrElse(
                (String uuid) -> log.info("UUID is alredy present {} ", uuid),
                () -> setUuidByTransaction(transaction)
            );
    }

    public static void invoke(PaymentPipeDTO transactionPipeDTO) {
        Optional
            .ofNullable(transactionPipeDTO.getTransaction())
            .ifPresent((Transaction trx) -> invoke(trx));
    }

    private static void setUuidByTransaction(Transaction transaction) {
        Optional
            .ofNullable(transaction)
            .ifPresent(
                (Transaction trx) ->
                    ThreadContext.put(
                        UUID_KEY,
                        Optional
                            .ofNullable(transaction.getUuid())
                            .orElse(StringUtils.EMPTY)
                    )
            );
    }

    private static String getUuid(PaymentRequestDTO transactionDTO) {
        return Optional
            .ofNullable(transactionDTO.getTransactionUuid())
            .orElse(
                Optional
                    .ofNullable(transactionDTO.getEntity())
                    .orElse(EntityDTO.builder().build())
                    .getReference()
            );
    }
}
