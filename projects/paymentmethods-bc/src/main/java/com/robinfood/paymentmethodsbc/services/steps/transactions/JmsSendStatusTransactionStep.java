package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionDetailDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.JmsTransactionStatusChangeMapper;
import com.robinfood.paymentmethodsbc.mappers.TransactionMapper;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.publishers.TransactionMessagesPublisher;
import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.utils.TransactionLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsSendStatusTransactionStep implements StepActions {

    private final TransactionMessagesPublisher transactionMessagesPublisher;

    public JmsSendStatusTransactionStep(TransactionMessagesPublisher transactionMessagesPublisher) {
        this.transactionMessagesPublisher = transactionMessagesPublisher;
    }

    @BasicLog
    @Override
    public void invoke(Object pipe)
        throws PaymentStepException, EntityNotFoundException {

        if (pipe instanceof TransactionStatusPipeDTO) {
            TransactionStatusPipeDTO transactionStatusPipeDTO = (TransactionStatusPipeDTO) pipe;

            if (transactionStatusPipeDTO.isNotifyStatus()) {
                sendChangeStatusMessage(
                    transactionStatusPipeDTO.getTransaction(),
                    transactionStatusPipeDTO.getTransactionDetail()
                );
            }

            return;
        }

        if (pipe instanceof JmsTransactionStatusPipeDTO) {
            JmsTransactionStatusPipeDTO transactionStatusPipeDTO = (JmsTransactionStatusPipeDTO) pipe;

            if (transactionStatusPipeDTO.isNotifyStatus()) {
                sendChangeStatusMessage(
                    transactionStatusPipeDTO.getTransaction(),
                    transactionStatusPipeDTO.getTransactionDetail()
                );
            }

            return;
        }

        if (pipe instanceof PaymentPipeDTO) {
            PaymentPipeDTO paymentPipeDTO = (PaymentPipeDTO) pipe;

            if (paymentPipeDTO.isNotifyStatus()) {
                sendChangeStatusMessage(
                    paymentPipeDTO.getTransaction(),
                    paymentPipeDTO.getTransactionDetail()
                );
            }

            return;
        }

        throw new PaymentStepException(
            StepType.JMS_SEND_STATUS_TRANSACTION,
            String.format(
                "No se ha realizado ninguna acción en %s",
                getClass().getSimpleName()
            )
        );
    }

    private void sendChangeStatusMessage(Transaction transaction,
        TransactionDetail transactionDetail) {

        TransactionLogger.invoke(transaction);
        updateTransactionStatus(transaction);

        try {

            JmsTransactionStatusChangeDTO jmsTransactionStatusChangeDTO = JmsTransactionStatusChangeMapper
                .getJmsTransactionStatusChangeDTO(transaction, transactionDetail);

            JmsTransactionDetailDTO jmsTransactionDetailDTO = JmsTransactionStatusChangeMapper
                .getJmsTransactionDetailDTO(transaction);

            transactionMessagesPublisher.notifyChangeStatusTransaction(
                jmsTransactionStatusChangeDTO, jmsTransactionDetailDTO);

        } catch (Exception e) {
            log.error("Error sending message for notify change status to sqs", e);
        } finally {
            TransactionLogger.clear();
        }
    }

    private Transaction updateTransactionStatus(Transaction transaction) {
        Long transactionStatusId = TransactionMapper.getFinalTransactionStatusId(transaction);
        transaction.getTransactionStatus().setId(transactionStatusId);
        return transaction;
    }
}
