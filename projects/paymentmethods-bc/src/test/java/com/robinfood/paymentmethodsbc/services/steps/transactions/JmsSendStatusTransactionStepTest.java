package com.robinfood.paymentmethodsbc.services.steps.transactions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.publishers.TransactionMessagesPublisher;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JmsSendStatusTransactionStepTest {

    @Mock
    private TransactionMessagesPublisher transactionMessagesPublisher;

    @InjectMocks
    private JmsSendStatusTransactionStep jmsSendStatusTransactionStep;

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ShouldBeOk_When_IsTransactionStatusPipeDTO()
        throws PaymentMethodsException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        when(transactionMessagesPublisher.notifyChangeStatusTransaction(any(), any()))
            .thenReturn(ResponseDTO.builder().build());

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, times(1))
            .notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ThrowException_When_IsTransactionStatusPipeDTO()
        throws PaymentMethodsException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        when(transactionMessagesPublisher.notifyChangeStatusTransaction(any(), any()))
            .thenReturn(ResponseDTO.builder().build());

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, times(1))
            .notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ShouldBeOk_When_NotNotifyTransactionStatusPipeDTO()
        throws PaymentMethodsException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.setNotifyStatus(false);

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, never()).notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ShouldBeOk_When_IsJmsTransactionStatusPipeDTO()
        throws PaymentMethodsException {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTOWithAdditionalInfo();
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        when(transactionMessagesPublisher.notifyChangeStatusTransaction(any(), any()))
            .thenReturn(ResponseDTO.builder().build());

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, times(1))
            .notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ShouldBeOk_When_IsJmsTransactionStatusPipeDTONotNotify()
        throws PaymentMethodsException {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTOWithAdditionalInfo();
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());
        pipe.setNotifyStatus(false);

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, never()).notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ShouldBeOk_When_IsPaymentPipeDTO()
        throws PaymentMethodsException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        when(transactionMessagesPublisher.notifyChangeStatusTransaction(any(), any()))
            .thenReturn(ResponseDTO.builder().build());

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, times(1))
            .notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ShouldBeOk_When_NotNotifyPaymentPipeDTO()
        throws PaymentMethodsException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.setNotifyStatus(false);

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, never()).notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStep_ThrowBaseException_When_IsPaymentPipeDTO()
        throws PaymentMethodsException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        assertThrows(
            PaymentStepException.class,
            () -> jmsSendStatusTransactionStep.invoke(new Object()),
            "BaseException"
        );
        verify(transactionMessagesPublisher, never()).notifyChangeStatusTransaction(any(), any());
    }


    @Test
    void test_InvokeJmsSendStatusTransactionStep_ShouldBeOk_When_TransactionStatusParentIsNotNull()
        throws PaymentMethodsException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransaction().getTransactionStatus().setId(8L);
        ;
        pipe.getTransaction().getTransactionStatus().setTransactionStatusParentId(3L);
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        when(transactionMessagesPublisher.notifyChangeStatusTransaction(any(), any()))
            .thenReturn(ResponseDTO.builder().build());

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, times(1))
            .notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void tes_tInvokeJmsSendStatusTransactionStep_ShouldBeOk_When_TransactionStatusParentIsZero()
        throws PaymentMethodsException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransaction().getTransactionStatus().setId(1L);
        pipe.getTransaction().getTransactionStatus().setTransactionStatusParentId(0L);
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        when(transactionMessagesPublisher.notifyChangeStatusTransaction(any(), any()))
            .thenReturn(ResponseDTO.builder().build());

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, times(1))
            .notifyChangeStatusTransaction(any(), any());
    }

    @Test
    void test_InvokeJmsSendStatusTransactionStepStep_ThrowsException_When_TransactionMessagesPublisherThrowsException()
        throws PaymentMethodsException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransaction().getTransactionStatus().setId(1L);
        pipe.getTransaction().getTransactionStatus().setTransactionStatusParentId(0L);
        pipe.getTransaction().setUpdatedAt(LocalDateTime.now());

        when(transactionMessagesPublisher.notifyChangeStatusTransaction(any(), any()))
            .thenThrow(mock(PaymentMethodsException.class));

        assertAll(() -> jmsSendStatusTransactionStep.invoke(pipe));

        verify(transactionMessagesPublisher, times(1))
            .notifyChangeStatusTransaction(any(), any());
    }
}
