package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionGenerateDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.publishers.TransactionMessagesPublisher;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.LoggerReportUtils;

import lombok.extern.slf4j.Slf4j;

import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_IS_REQUESTED;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_STATUS_ERROR;
import static com.robinfood.paymentmethodsbc.constants.LoggerPaymentConstants.PAYMENT_STATUS_REQUESTED;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsSendGenerateTransactionStep implements StepActions {
    private final TransactionMessagesPublisher transactionMessagesPublisher;

    public JmsSendGenerateTransactionStep(
        TransactionMessagesPublisher transactionMessagesPublisher
    ) {
        this.transactionMessagesPublisher = transactionMessagesPublisher;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {
        if (pipe instanceof PaymentPipeDTO) {
            PaymentPipeDTO transactionPipeDTO = (PaymentPipeDTO) pipe;
            try {

                LoggerReportUtils.doPaymentLogger(
                    PAYMENT_STATUS_REQUESTED, PAYMENT_IS_REQUESTED, transactionPipeDTO
                );
                transactionMessagesPublisher.sendGenerateMessage(
                    JmsTransactionGenerateDTO
                        .builder()
                        .retries(0L)
                        .transaction(transactionPipeDTO.getTransactionRequestDTO())
                        .build()
                );
            } catch(Exception e) {
                log.error("Error sending message to sqs", e);
                LoggerReportUtils.doPaymentLogger(
                    PAYMENT_STATUS_ERROR, e.getLocalizedMessage(), transactionPipeDTO
                );
            }

            return;
        }

        throw new PaymentStepException(
            StepType.JMS_SEND_GENERATE_TRANSACTION,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }
}
