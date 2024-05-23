package com.robinfood.paymentmethodsbc.services.steps.transactions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.publishers.TransactionMessagesPublisher;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JmsSendGenerateTransactionStepTest {
    @Mock
    private TransactionMessagesPublisher transactionMessagesPublisher;

    @InjectMocks
    private JmsSendGenerateTransactionStep jmsSendGenerateTransactionStep;

    @Test
    public void testInvokeJmsSendGenerateTransactionStepStepShouldBeOkTransactionStatusPipeDTO()
        throws PaymentMethodsException {

        when(transactionMessagesPublisher.sendGenerateMessage(any()))
            .thenReturn(ResponseDTO.builder().build());

        assertAll(
            () ->
                jmsSendGenerateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeJmsSendGenerateTransactionStepStepThrowException()
        throws PaymentMethodsException {

        when(transactionMessagesPublisher.sendGenerateMessage(any()))
            .thenThrow(new PaymentMethodsException("{\"code\":500, \"message\": \"error\"}"));

        assertAll(
            () ->
                jmsSendGenerateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    public void testInvokeJmsSendGenerateTransactionStepStepShouldBeOkBaseException() {
        assertThrows(
            PaymentStepException.class,
            () ->
                jmsSendGenerateTransactionStep.invoke(
                    TransactionSamples.getRefundResponseDTO()
                ),
            "BaseException"
        );
    }
}
