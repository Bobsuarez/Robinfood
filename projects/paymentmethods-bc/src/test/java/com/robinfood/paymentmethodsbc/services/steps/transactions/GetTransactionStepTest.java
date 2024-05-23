package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.PaymentPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.RefundPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTransactionStepTest {
    @Mock
    private TransactionsCommonOperations transactionsCommonOperations;

    @Mock
    private TransactionsConfig transactionsConfig;

    @InjectMocks
    private GetTransactionStep getTransactionStep;

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOk()
        throws EntityNotFoundException {
        PaymentPipeDTO pipe = TransactionSamples.getTransactionCreatePipeDTO();

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.getTransactionDetailByTransactionId(
                anyLong()
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeOk()
        throws EntityNotFoundException {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();

        Transaction transaction = TransactionSamples.getTransaction();
        transaction.setSucceeded(false);

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsConfig.isStatusAccepted(anyLong()))
            .thenReturn(true);

        when(transactionsCommonOperations.getTransactionDetailByTransactionId(anyLong()))
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeRejected() throws EntityNotFoundException {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();

        Transaction transaction = TransactionSamples.getTransaction();
        transaction.setSucceeded(false);

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsConfig.isStatusAccepted(anyLong()))
            .thenReturn(false);

        when(transactionsConfig.isStatusRefundRejected(anyLong()))
            .thenReturn(true);

        when(transactionsCommonOperations.getTransactionDetailByTransactionId(anyLong()))
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeNotRejected() throws EntityNotFoundException {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();

        Transaction transaction = TransactionSamples.getTransaction();
        transaction.setSucceeded(false);

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsConfig.isStatusAccepted(anyLong()))
            .thenReturn(true);

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeExceptionWhenNotSucceeded()
        throws EntityNotFoundException {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();
        Transaction transaction = TransactionSamples.getTransaction();
        transaction.setSucceeded(false);

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(transaction);

        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(pipe)
        );
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeExceptionWhenNotAcepted()
        throws EntityNotFoundException {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();
        Transaction transaction = TransactionSamples.getTransaction();
        transaction.setSucceeded(true);

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(transaction);

        when(transactionsConfig.isStatusAccepted(anyLong()))
            .thenReturn(false);

        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(pipe)
        );
    }

    @Test
    void testInvokeForRefundPipeDTOShouldBeExceptionWhenNotRefundRejected()
        throws EntityNotFoundException {
        RefundPipeDTO pipe = TransactionSamples.getRefundPipeDTO();
        Transaction transaction = TransactionSamples.getTransaction();
        transaction.setSucceeded(true);

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(transaction);

        when(transactionsConfig.isStatusRefundRejected(anyLong()))
            .thenReturn(false);

        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(pipe)
        );
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeOkKeyId()
        throws EntityNotFoundException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setKey(AppConstants.STATUS_KEY_ID);
        pipe.getTransactionStatusUpdateRequestDTO().setIdentificator("10");

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeErrorKeyId() {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setKey(AppConstants.STATUS_KEY_ID);

        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(pipe)
        );
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeOkKeyUUid()
        throws EntityNotFoundException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setKey(AppConstants.STATUS_KEY_UUID);

        when(transactionsCommonOperations.getTransactionByUuid(anyString()))
            .thenReturn(TransactionSamples.getTransaction());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeOkKeyCodePurchase()
        throws EntityNotFoundException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setKey(AppConstants.STATUS_KEY_CODE);
        pipe.getTransactionStatusUpdateRequestDTO().setIdentificator("id");
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setType(AppConstants.TRANSACTION_TYPE_PURCHASE);
        when(
            transactionsCommonOperations.getTransactionDetailByCode(
                anyString()
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeOkKeyCodeRefund()
        throws EntityNotFoundException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setKey(AppConstants.STATUS_KEY_CODE);
        pipe.getTransactionStatusUpdateRequestDTO().setIdentificator("id");
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setType(AppConstants.TRANSACTION_TYPE_REFUND);
        when(
            transactionsCommonOperations.getTransactionStatusLogByCodeAndStatusId(
                anyString(),
                anyLong()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeOkKeyReferencePurchase()
        throws EntityNotFoundException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setKey(AppConstants.STATUS_KEY_REFERENCE);
        pipe.getTransactionStatusUpdateRequestDTO().setIdentificator("id");
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setType(AppConstants.TRANSACTION_TYPE_PURCHASE);

        when(
            transactionsCommonOperations.getTransactionDetailByReference(
                anyString()
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeOkKeyReferenceRefund()
        throws EntityNotFoundException {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setKey(AppConstants.STATUS_KEY_REFERENCE);
        pipe.getTransactionStatusUpdateRequestDTO().setIdentificator("id");
        pipe
            .getTransactionStatusUpdateRequestDTO()
            .setType(AppConstants.TRANSACTION_TYPE_REFUND);

        when(
            transactionsCommonOperations.getTransactionStatusLogByReferenceAndStatusId(
                anyString(),
                anyLong()
            )
        )
            .thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testInvokeForTransactionStatusPipeDTOShouldBeOkKeyNotFound() {
        TransactionStatusPipeDTO pipe = TransactionSamples.getTransactionStatusPipeDTO();
        pipe.getTransactionStatusUpdateRequestDTO().setKey("prueba");

        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(pipe)
        );
    }

    @Test
    void testJmsInvokeForTransactionStatusPipeDTOShouldBeOkKeyId()
        throws EntityNotFoundException {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTO();
        pipe.setJmsUpdateTransactionStatusDTO(
            JmsUpdateTransactionStatusDTO.builder()
                .key("id")
                .identificator("10")
                .build()
        );

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testJmsInvokeForTransactionStatusPipeDTOShouldBeErrorKeyId() {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTO();
        pipe.setJmsUpdateTransactionStatusDTO(
            JmsUpdateTransactionStatusDTO.builder()
                .key("id")
                .identificator(null)
                .build()
        );

        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(pipe)
        );
    }

    @Test
    void testJmsInvokeForTransactionStatusPipeDTOShouldBeOkKeyUUid()
        throws EntityNotFoundException {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTO();
        pipe.setJmsUpdateTransactionStatusDTO(
            JmsUpdateTransactionStatusDTO.builder()
                .key("uuid")
                .identificator("10")
                .build()
        );

        when(transactionsCommonOperations.getTransactionByUuid(anyString()))
            .thenReturn(TransactionSamples.getTransaction());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testJmsInvokeForTransactionStatusPipeDTOShouldBeOkKeyCode()
        throws EntityNotFoundException {

        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTO();
        pipe.setJmsUpdateTransactionStatusDTO(
            JmsUpdateTransactionStatusDTO.builder()
                .key("code")
                .identificator("10")
                .build()
        );

        when(
            transactionsCommonOperations.getTransactionDetailByCode(
                anyString()
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testJmsInvokeForTransactionStatusPipeDTOShouldBeOkKeyReference()
        throws EntityNotFoundException {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTO();
        pipe.setJmsUpdateTransactionStatusDTO(
            JmsUpdateTransactionStatusDTO.builder()
                .key("reference")
                .identificator("10")
                .build()
        );

        when(
            transactionsCommonOperations.getTransactionDetailByReference(
                anyString()
            )
        )
            .thenReturn(TransactionSamples.getTransactionDetail());

        assertAll(() -> getTransactionStep.invoke(pipe));
    }

    @Test
    void testJmsInvokeForTransactionStatusPipeDTOShouldBeOkKeyNotFound() {
        JmsTransactionStatusPipeDTO pipe = TransactionSamples.getJmsTransactionStatusPipeDTO();
        pipe.setJmsUpdateTransactionStatusDTO(
            JmsUpdateTransactionStatusDTO.builder()
                .key("prueba")
                .identificator("10")
                .build()
        );

        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(pipe)
        );
    }

    @Test
    void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> getTransactionStep.invoke(new Object())
        );
    }
}
