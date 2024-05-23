package com.robinfood.paymentmethodsbc.asynchronus;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.ERROR_ENTITY_NOT_FOUND_OR_BASE_EXCEPTION;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.ERROR_UPDATE_TRANSACTION_STATUS;
import static com.robinfood.paymentmethodsbc.constants.MessageConstants.LOG_TRACE_FORMAT_ERROR_PROCESSING;
import static com.robinfood.paymentmethodsbc.utils.JsonUtils.convertToJson;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.services.TransactionService;
import com.robinfood.paymentmethodsbc.utils.LoggerReportUtils;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionMessagesAsync {

    private final TransactionService transactionService;

    public TransactionMessagesAsync(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    private void createTransactionProcess(PaymentRequestDTO paymentRequestDTO) {
        TransactionLogger.invoke(paymentRequestDTO);
        try {
            transactionService.doPayment(paymentRequestDTO);
        } catch (BaseException | EntityNotFoundException e) {
            log.error("createTransactionProcess Exception:", e);
        } catch (Exception ex) {
            log.error("createTransactionProcess general Exception:", ex);
        } finally {
            TransactionLogger.clear();
        }
    }

    public void createTransactionAsync(PaymentRequestDTO paymentRequestDTO) {
        CompletableFuture.runAsync(
            () -> createTransactionProcess(paymentRequestDTO)
        ).exceptionally((Throwable throwable) -> {
            log.error("createTransactionAsync Exception:", throwable);
            return null;
        });
    }

    private void updateTransactionProcess(
        JmsUpdateTransactionStatusDTO updateTransactionStatusDTO
    ) {
        TransactionLogger.invoke(updateTransactionStatusDTO);
        try {
            String jsonMessage = convertToJson(updateTransactionStatusDTO);
            transactionService.updateTransactionStatusJMS(updateTransactionStatusDTO, jsonMessage);
            TransactionLogger.clear();
        } catch (PaymentStepException | EntityNotFoundException e) {
            log.error("updateTransactionProcess Exception:", e);
            LoggerReportUtils.errorUpdateStatusTransactionLogger(
                ERROR_UPDATE_TRANSACTION_STATUS.concat(LOG_TRACE_FORMAT_ERROR_PROCESSING),
                e.getMessage(),
                ERROR_ENTITY_NOT_FOUND_OR_BASE_EXCEPTION
            );
        } finally {
            TransactionLogger.clear();
        }
    }

    public void updateTransactionAsync(JmsUpdateTransactionStatusDTO updateTransactionStatusDTO) {
        CompletableFuture.runAsync(
            () -> updateTransactionProcess(updateTransactionStatusDTO)
        ).exceptionally((Throwable throwable) -> {
            log.error("updateTransactionAsync Exception:", throwable);
            return null;
        });
    }

    public void refundTransactionAsync(
        JmsEntityRefundRequestDTO refundRequestDTO
    ) {
        CompletableFuture.runAsync(() -> transactionService.entityRefund(refundRequestDTO))
            .exceptionally((Throwable throwable) -> {
                log.error("refundTransactionAsync Exception:", throwable);
                return null;
            });
    }
}
