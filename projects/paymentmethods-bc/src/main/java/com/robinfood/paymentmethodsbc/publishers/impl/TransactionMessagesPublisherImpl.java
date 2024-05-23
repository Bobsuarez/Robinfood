package com.robinfood.paymentmethodsbc.publishers.impl;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionGenerateDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.publishers.TransactionMessagesPublisher;
import com.robinfood.paymentmethodsbc.repositories.TransactionMessagesCreateRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionMessagesNotifyRepository;
import com.robinfood.paymentmethodsbc.security.SSOTokenUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import static com.robinfood.paymentmethodsbc.constants.JwtTokenConstants.BEARER;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_NOT_SENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_NOT_SENT_DESCRIPTION;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_CHANGE_NOT_SENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_SENT;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_SENT_DESCRIPTION;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_MESSAGE_CHANGE_SENT;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;
import static com.robinfood.paymentmethodsbc.utils.LoggerReportUtils.doGeneratePaymentLogger;
import static com.robinfood.paymentmethodsbc.utils.LoggerReportUtils.doNotifyStatusLogger;

@Slf4j
@Component
public class TransactionMessagesPublisherImpl implements TransactionMessagesPublisher {
    private final SSOTokenUtil ssoTokenUtil;
    private final RetryTemplate retryTemplate;
    private final TransactionMessagesCreateRepository transactionMessagesCreateRepository;
    private final TransactionMessagesNotifyRepository transactionMessagesNotifyRepository;
    private int counter;

    public TransactionMessagesPublisherImpl(
        SSOTokenUtil ssoTokenUtil,
        RetryTemplate retryTemplate,
        TransactionMessagesCreateRepository transactionMessagesCreateRepository,
        TransactionMessagesNotifyRepository transactionMessagesNotifyRepository
    ) {
        this.ssoTokenUtil = ssoTokenUtil;
        this.retryTemplate = retryTemplate;
        this.transactionMessagesCreateRepository = transactionMessagesCreateRepository;
        this.transactionMessagesNotifyRepository = transactionMessagesNotifyRepository;
    }

    @Override
    @BasicLog
    public ResponseDTO<Object> sendGenerateMessage(
        JmsTransactionGenerateDTO message
    ) throws PaymentMethodsException {
        counter = 0;
        return retryTemplate.execute(
            (RetryContext context) -> createTransaction(message)
        );
    }

    @Override
    @BasicLog
    public ResponseDTO<Object> notifyChangeStatusTransaction(
        JmsTransactionStatusChangeDTO message,
        JmsTransactionDetailDTO detail
    ) throws PaymentMethodsException {
        counter = 0;
        return retryTemplate.execute(
            (RetryContext context) -> notifyChangeStatus(message, detail)
        );
    }

    private ResponseDTO<Object> createTransaction(
        JmsTransactionGenerateDTO message
    ) throws PaymentMethodsException {
        counter++;
        try {
            ResponseDTO<Object> response = transactionMessagesCreateRepository.createTransaction(
                BEARER.concat(ssoTokenUtil.getPublicJwtToken()), message.getTransaction());
            doGeneratePaymentLogger(
                PAYMENT_MESSAGE_SENT,
                PAYMENT_MESSAGE_SENT_DESCRIPTION,
                message, convertToJson(response)
            );
            return response;
        } catch(FeignException e) {
            log.error(PAYMENT_MESSAGE_NOT_SENT_DESCRIPTION.concat(" in try ").concat(String.valueOf(counter)));
            doGeneratePaymentLogger(
                PAYMENT_MESSAGE_NOT_SENT,
                PAYMENT_MESSAGE_NOT_SENT_DESCRIPTION.concat(", try ").concat(String.valueOf(counter)),
                message, "Error creating a transaction"
            );
            throw new PaymentMethodsException(e);
        }
    }

    private ResponseDTO<Object> notifyChangeStatus(
        JmsTransactionStatusChangeDTO message,
        JmsTransactionDetailDTO detail
    ) throws PaymentMethodsException {
        counter++;
        try {
            ResponseDTO<Object> response = transactionMessagesNotifyRepository.notifyChangeStatusTransaction(
                BEARER.concat(ssoTokenUtil.getPublicJwtToken()), message);
            doNotifyStatusLogger(
                PAYMENT_MESSAGE_CHANGE_SENT,
                PAYMENT_MESSAGE_SENT_DESCRIPTION,
                message, detail, convertToJson(response)
            );
            return response;
        } catch(FeignException e) {
            log.error(PAYMENT_MESSAGE_NOT_SENT_DESCRIPTION.concat(" in try ").concat(String.valueOf(counter)));
            doNotifyStatusLogger(
                PAYMENT_MESSAGE_CHANGE_NOT_SENT,
                PAYMENT_MESSAGE_NOT_SENT_DESCRIPTION.concat(", try ").concat(String.valueOf(counter)),
                message, detail, "Error notifying change status"
            );
            throw new PaymentMethodsException(e);
        }
    }
}
