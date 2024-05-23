package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.components.providers.BCIProvider;
import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.sample.PaymentGatewaySamples;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.FailedTransactionsOperations;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentStepTest {
    @Mock
    private TransactionsCommonOperations transactionsCommonOperations;

    @Mock
    private BCIProvider paymentGatewayProvider;

    @Mock
    private FailedTransactionsOperations failedTransactionsOperations;

    @Mock
    private TransactionsConfig transactionsConfig;

    @InjectMocks
    private PaymentStep generateTransactionStep;

    @Test
    void testInvokeForTransactionPipeDTOShouldBeOk()
        throws PaymentMethodsException, EntityNotFoundException {

        when(
                paymentGatewayProvider.doPayment(
                    any(SettingsDTO.class),
                    any(PaymentPipeDTO.class)
                )
            )
            .thenReturn(PaymentGatewaySamples.getPaymentResponseDTO());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionDetail(
                any(TransactionDetail.class)
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(
            () ->
                generateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    void testInvokeForTransactionPipeDTOShouldBeOkNotifying()
        throws PaymentMethodsException, EntityNotFoundException {

        when(
            paymentGatewayProvider.doPayment(
                any(SettingsDTO.class), any(PaymentPipeDTO.class)
            )
        ).thenReturn(PaymentGatewaySamples.getPaymentResponseDTO());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsCommonOperations.saveTransactionDetail(any(TransactionDetail.class)))
            .thenReturn(TransactionSamples.getTransactionDetail());

        when(transactionsCommonOperations.canNotifyStatus(any()))
            .thenReturn(true);

        assertAll(
            () ->
                generateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    void testInvokeForTransactionPipeDTOWhitErrorResponseShouldBeOk()
        throws PaymentMethodsException, EntityNotFoundException {

        when(
            paymentGatewayProvider.doPayment(
                any(SettingsDTO.class),
                any(PaymentPipeDTO.class)
            )
        )
            .thenReturn(PaymentGatewaySamples.getPaymentErrorResponseDTO());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionDetail(
                any(TransactionDetail.class)
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(
            () ->
                generateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    void testInvokeForTransactionPipeDTOWhitErrorBeOk()
        throws PaymentMethodsException, EntityNotFoundException {

        when(
            paymentGatewayProvider.doPayment(
                any(SettingsDTO.class),
                any(PaymentPipeDTO.class)
            )
        )
            .thenReturn(PaymentGatewaySamples.getPaymentErrorResponse());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionDetail(
                any(TransactionDetail.class)
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(
            () ->
                generateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    void testUpdateTransactionStatusRejectedBeOk()
        throws PaymentMethodsException, EntityNotFoundException {

        when(
            paymentGatewayProvider.doPayment(
                any(SettingsDTO.class),
                any(PaymentPipeDTO.class)
            )
        )
            .thenReturn(PaymentGatewaySamples.getPaymentErrorResponse());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionDetail(
                any(TransactionDetail.class)
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());
        when(
            transactionsConfig.isStatusRejected(anyLong()
            )
        ).thenReturn(true);

        assertAll(
            () ->
                generateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    void testInvokeForTransactionPipeDTOWithSucceededShouldBeOk()
        throws PaymentMethodsException, EntityNotFoundException {


        when(
            paymentGatewayProvider.doPayment(
                any(SettingsDTO.class),
                any(PaymentPipeDTO.class)
            )
        )
            .thenReturn(PaymentGatewaySamples.getPaymentResponseDTO());

        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class),
                anyString(),
                any(),
                any(),
                any()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            transactionsCommonOperations.saveTransaction(
                any(Transaction.class)
            )
        )
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionDetail(
                any(TransactionDetail.class)
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();
        pipe.getTransaction().setSucceeded(true);
        pipe.setNotifyStatus(true);
        assertAll(() -> generateTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForTransactionPipeDTOShouldBeErrorPaymentMethodsException()
        throws PaymentMethodsException {
        when(
                paymentGatewayProvider.doPayment(
                    any(SettingsDTO.class),
                    any(PaymentPipeDTO.class)
                )
            )
            .thenThrow(PaymentMethodsException.class);

        assertAll(
            () ->
                generateTransactionStep.invoke(
                    TransactionSamples.getTransactionCreatePipeDTO()
                )
        );
    }

    @Test
    void testInvokeNoImplementedPipeShouldBeOk() {
        assertThrows(
            PaymentStepException.class,
            () -> generateTransactionStep.invoke(new Object())
        );
    }
}
