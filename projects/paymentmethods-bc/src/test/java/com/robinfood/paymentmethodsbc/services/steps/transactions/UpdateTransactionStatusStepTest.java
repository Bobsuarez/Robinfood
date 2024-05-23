package com.robinfood.paymentmethodsbc.services.steps.transactions;

import com.robinfood.paymentmethodsbc.configs.TransactionsConfig;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.steps.SettingsDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentMethodsException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.components.providers.impl.BCIProviderImpl;
import com.robinfood.paymentmethodsbc.sample.BCITransactionStatusResultDTOSample;
import com.robinfood.paymentmethodsbc.sample.FailedTransactionsSample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.FailedTransactionsOperations;
import com.robinfood.paymentmethodsbc.services.TransactionsCommonOperations;
import com.robinfood.paymentmethodsbc.utils.FilterStatusNotificationUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.robinfood.paymentmethodsbc.sample.BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO;
import static com.robinfood.paymentmethodsbc.sample.BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTOTransactionStatusNull;
import static com.robinfood.paymentmethodsbc.sample.TransactionSamples.getTransactionStatusPipeDTOUnknow;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTransactionStatusStepTest {
    @Mock
    private TransactionsCommonOperations transactionsCommonOperations;

    @Mock
    private FailedTransactionsOperations failedTransactionsOperations;

    @Mock
    private BCIProviderImpl paymentGatewayProvider;

    @Mock
    private TransactionsConfig transactionsConfig;

    @InjectMocks
    private UpdateTransactionStatusStep updateTransactionStatusStep;

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusDifferentNull()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(
            getBCITransactionStatusResultDTO()
        );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTO()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusDifferentNullNotify()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(
            getBCITransactionStatusResultDTO()
        );

        try (MockedStatic<FilterStatusNotificationUtils> staticMock = mockStatic(FilterStatusNotificationUtils.class)) {
            staticMock.when(() -> FilterStatusNotificationUtils.isNotify(any(), any(), any(), any()))
                .thenReturn(true);

            assertAll(
                () ->
                    updateTransactionStatusStep.invoke(
                        TransactionSamples.getTransactionStatusPipeDTO()
                    )
            );
        }
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOOtherTransactionType()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionAccepted());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(
            getBCITransactionStatusResultDTO()
        );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTORefund()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOOtherTransactionTypeRefundAccepted()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatusRefundAccepted());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionAccepted());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(4L);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(
            getBCITransactionStatusResultDTO()
        );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTORefund()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOOtherTransactionTypeRefundNotAccepted()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionAccepted());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(transactionsConfig.getStatusId(anyString()))
            .thenReturn(5L);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(
            getBCITransactionStatusResultDTO()
        );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTORefund()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOAuthorizationCodeNotNull()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionAccepted());

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(
            transactionsCommonOperations.saveTransactionStatusLog(
                any(Transaction.class), anyString(), any(), any(), any()
            )
        ).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(
            getBCITransactionStatusResultDTO()
        );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTOAuthCodeNotNull()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatus()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionAccepted());

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO()
            );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTO()
                )
        );
    }
    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusIfPending()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionAccepted());

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO()
            );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTOOtherType()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusDifferentNullTransactionTypeUnknow()
        throws PaymentMethodsException {
        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(getBCITransactionStatusResultDTO());

        assertAll(() -> updateTransactionStatusStep.invoke(getTransactionStatusPipeDTOUnknow()));
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusNullTransactionTypeUnknow()
        throws PaymentMethodsException {
        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class), any(TransactionStatusPipeDTO.class)
            )
        ).thenReturn(getBCITransactionStatusResultDTOTransactionStatusNull());

        assertAll(() -> updateTransactionStatusStep.invoke(getTransactionStatusPipeDTOUnknow()));
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionIsNull()
        throws PaymentMethodsException {
        BCITransactionStatusResponseDTO resultBCI =
            getBCITransactionStatusResultDTO();
        resultBCI.getTransaction().setTransactionStatus(null);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(resultBCI);

        assertAll(
            () -> updateTransactionStatusStep.invoke(TransactionSamples.getTransactionStatusPipeDTO())
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldErrorWhenEntityNotFoundException()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenThrow(EntityNotFoundException.class);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                getBCITransactionStatusResultDTO()
            );

        assertThrows(
            EntityNotFoundException.class,
            () -> updateTransactionStatusStep.invoke(TransactionSamples.getTransactionStatusPipeDTO()),
            "EntityNotFoundException"
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldErrorWhenPaymentMethodsException()
        throws PaymentMethodsException {
        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenThrow(PaymentMethodsException.class);

        assertThrows(
            PaymentStepException.class,
            () -> updateTransactionStatusStep.invoke(
                TransactionSamples.getTransactionStatusPipeDTO()
            ),
            "BaseException"
        );
    }

    @Test
    void testInvokeForJmsTransactionStatusPipeDTOShouldBeOk() throws EntityNotFoundException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction1());

        when(transactionsConfig.isStatusAccepted(anyLong()))
            .thenReturn(true);

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsCommonOperations.saveTransactionDetail(any(TransactionDetail.class)))
            .thenReturn(TransactionSamples.getTransactionDetail());

        when(transactionsCommonOperations.saveTransactionLog(
            any(Transaction.class), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionLog());

        when(transactionsCommonOperations.saveTransactionStatusLog(
            any(Transaction.class), anyString(), anyString(), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(
            () -> updateTransactionStatusStep.invoke(
                TransactionSamples.getJmsTransactionStatusPipeDTO()
            )
        );
    }

    @Test
    void testInvokeForJmsTransactionStatusPipeDTOShouldBeOkWithoutCodeAndReference() throws EntityNotFoundException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction1());

        when(transactionsConfig.isStatusAccepted(anyLong()))
            .thenReturn(true);

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransaction());

        when(transactionsCommonOperations.saveTransactionDetail(any(TransactionDetail.class)))
            .thenReturn(TransactionSamples.getTransactionDetailWithoutCodeAndReference());

        when(transactionsCommonOperations.saveTransactionLog(
            any(Transaction.class), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionLog());

        when(transactionsCommonOperations.saveTransactionStatusLog(
            any(Transaction.class), anyString(), anyString(), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(
            () -> updateTransactionStatusStep.invoke(
                TransactionSamples.getJmsTransactionStatusPipeDTOWithoutCodeAndReference()
            )
        );
    }

    @Test
    void testInvokeForJmsTransactionStatusPipeDTOShouldBeOkWithAdditionalInfo() throws EntityNotFoundException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction1());

        when(transactionsConfig.isStatusAccepted(anyLong())).thenReturn(true);

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransactionWithAuthorizationCode());

        when(transactionsCommonOperations.saveTransactionDetail(any(TransactionDetail.class)))
            .thenReturn(TransactionSamples.getTransactionDetailWithAdditionalData());

        when(transactionsCommonOperations.saveTransactionLog(
            any(Transaction.class), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionLog());

        when(transactionsCommonOperations.saveTransactionStatusLog(
            any(Transaction.class), anyString(), anyString(), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(
            () -> updateTransactionStatusStep.invoke(
                TransactionSamples.getJmsTransactionStatusPipeDTOWithAdditionalInfo()
            )
        );
    }

    @Test
    void testInvokeForJmsTransactionStatusPipeDTOShouldBeRejected() throws EntityNotFoundException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction1());

        when(transactionsConfig.isStatusRejected(anyLong())).thenReturn(true);

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransactionWthiStatusRejected());

        when(transactionsCommonOperations.saveTransactionDetail(any(TransactionDetail.class)))
            .thenReturn(TransactionSamples.getTransactionDetail());

        when(transactionsCommonOperations.saveTransactionLog(
            any(Transaction.class), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionLog());

        when(transactionsCommonOperations.saveTransactionStatusLog(
            any(Transaction.class), anyString(), anyString(), anyString(), anyString()
        )).thenReturn(TransactionSamples.getTransactionStatusLog());

        assertAll(
            () -> updateTransactionStatusStep.invoke(
                TransactionSamples.getJmsTransactionStatusPipeDTO()
            )
        );
    }

    @Test
    void testInvokeForJMSTransactionStatusPipeDTOFailedShouldBeOk() throws EntityNotFoundException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatusRejected());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionPending());

        when(transactionsConfig.isStatusAccepted(anyLong()))
            .thenReturn(false);

        when(transactionsCommonOperations.saveTransaction(any(Transaction.class)))
            .thenReturn(TransactionSamples.getTransactionPending());

        when(transactionsCommonOperations.saveTransactionDetail(any(TransactionDetail.class)))
            .thenReturn(TransactionSamples.getTransactionDetail());

        when(transactionsCommonOperations.saveTransactionLog(
            any(Transaction.class),
            anyString(),
            anyString()
        )).thenReturn(TransactionSamples.getTransactionLog());

        when(transactionsCommonOperations.saveTransactionStatusLog(
            any(Transaction.class),
            anyString(),
            anyString(),
            anyString(),
            anyString()
        )).thenReturn(TransactionSamples.getTransactionStatusLog());

        when(transactionsConfig.isStatusRejected(TransactionSamples.getTransactionWthiStatusRejected().getTransactionStatus().getId()))
            .thenReturn(true);

        when(failedTransactionsOperations.saveFailedTransaction(
            any(Transaction.class),
            any(),
            any(),
            any()
        )).thenReturn(FailedTransactionsSample.getFailedTransaction());

        assertAll(
            () -> updateTransactionStatusStep.invoke(
                TransactionSamples.getJmsTransactionStatusPipeDTO()
            )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusAccepted()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus1());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransaction1());

        when(transactionsConfig.getStatusId(anyString())).thenReturn(1L);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO()
            );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTODifferentGateway()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusValidateGateway()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus1());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionValidateGateway());

        when(transactionsConfig.getStatusId(anyString())).thenReturn(2L);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO()
            );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTODifferentGateway()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusValidateDifferentGateway()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionValidateDifferentGateway());

        when(transactionsConfig.getStatusId(anyString())).thenReturn(2L);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO()
            );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTODifferentGateway()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetTransactionStatusValidateDifferentStatus()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus1());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionValidateDifferentGatewayStatus());

        when(transactionsConfig.getStatusId(anyString())).thenReturn(1L);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO()
            );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTODifferentGateway()
                )
        );
    }

    @Test
    void testInvokeForTransactionCreatePipeDTOShouldBeOkGetListStatusRedebanNotNotification()
        throws EntityNotFoundException, PaymentMethodsException {
        when(transactionsCommonOperations.getTransactionStatusById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionStatus());

        when(transactionsCommonOperations.getTransactionById(anyLong()))
            .thenReturn(TransactionSamples.getTransactionValidateDifferentGateway());

        when(transactionsConfig.getStatusId(anyString())).thenReturn(2L);

        when(
            paymentGatewayProvider.doValidateStatus(
                any(SettingsDTO.class),
                any(TransactionStatusPipeDTO.class)
            )
        )
            .thenReturn(
                BCITransactionStatusResultDTOSample.getBCITransactionStatusResultDTO()
            );

        assertAll(
            () ->
                updateTransactionStatusStep.invoke(
                    TransactionSamples.getTransactionStatusPipeDTOListStatusRedebanNotNotification()
                )
        );
    }

    @Test
    void testInvokeNoImplementedPipeShouldBeError() {
        assertThrows(
            PaymentStepException.class,
            () -> updateTransactionStatusStep.invoke(new Object()),
            "BaseException"
        );
    }
}
