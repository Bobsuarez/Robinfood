package com.robinfood.paymentmethodsbc.services.impl;

import com.robinfood.paymentmethodsbc.configs.PlatformTypeConfig;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.steps.JmsTransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.dto.steps.TransactionStatusPipeDTO;
import com.robinfood.paymentmethodsbc.enums.PlatformType;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.TransactionMapper;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.repositories.GeneralPaymentMethodsRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionDetailsRepository;
import com.robinfood.paymentmethodsbc.repositories.TransactionsRepository;
import com.robinfood.paymentmethodsbc.sample.GeneralPaymentMethodSample;
import com.robinfood.paymentmethodsbc.sample.TransactionSamples;
import com.robinfood.paymentmethodsbc.services.steps.StepGroupDefinitions;
import com.robinfood.paymentmethodsbc.utils.StepsControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static com.robinfood.paymentmethodsbc.sample.TransactionSamples.getTransaction;
import static com.robinfood.paymentmethodsbc.sample.TransactionSamples.getTransactionRequestDTO;
import static com.robinfood.paymentmethodsbc.sample.TransactionSamples.getTransactionResponseDTO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    static {
        try {
            mockStatic(TransactionMapper.class);
        } catch (Exception ignored) {
        }
    }

    @Mock
    private GeneralPaymentMethodsRepository generalPaymentMethodsRepository;

    @Mock
    private PlatformTypeConfig platformTypeConfig;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Mock
    private TransactionsRepository transactionsRepository;

    @Mock
    private TransactionDetailsRepository transactionDetailsRepository;

    @Test
    void testCreatePaymentInitialTransactionShouldOk() throws BaseException, EntityNotFoundException {
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(GeneralPaymentMethodSample.getGeneralPaymentMethod()));

        when(platformTypeConfig.getPlatformById(anyLong())).thenReturn(PlatformType.GATEWAY);

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.INITIAL_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO()).when(TransactionMapper.class);

            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(
                () ->
                    transactionServiceImpl.createPaymentInitialTransaction(
                        getTransactionRequestDTO()
                    )
            );
        }
    }

    @Test
    void testCreatePaymentInitialTransactionShouldBaseExceptionNotFoundException() {
        GeneralPaymentMethod generalPaymentMethod = GeneralPaymentMethodSample.getGeneralPaymentMethod();
        generalPaymentMethod.setPaymentGateway(null);
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(generalPaymentMethod));

        assertThrows(
            BaseException.class,
            () ->
                transactionServiceImpl.createPaymentInitialTransaction(
                    getTransactionRequestDTO()
                ),
            "BaseException"
        );
    }

    @Test
    void testCreatePaymentInitialTransactionPlatformNotFoundShouldBeException() {
        GeneralPaymentMethod generalPaymentMethod = GeneralPaymentMethodSample.getGeneralPaymentMethod();
        generalPaymentMethod.getPaymentGateway().setPlatform(null);
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(generalPaymentMethod));

        assertThrows(
            BaseException.class,
            () ->
                transactionServiceImpl.createPaymentInitialTransaction(
                    getTransactionRequestDTO()
                ),
            "BaseException"
        );
    }

    @Test
    void testCreatePaymentInitialTransactionShouldEntityNotFoundException() {
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.empty());

        assertThrows(
            EntityNotFoundRuntimeException.class,
            () -> transactionServiceImpl.createPaymentInitialTransaction(
                getTransactionRequestDTO()
            ),
            "EntityNotFoundRuntimeException"
        );
    }

    @Test
    void testDoPaymentShouldBeOk() throws BaseException, EntityNotFoundException {
        when(generalPaymentMethodsRepository.findByIdAndStatus(anyLong(), anyBoolean()))
            .thenReturn(Optional.of(GeneralPaymentMethodSample.getGeneralPaymentMethod()));

        when(platformTypeConfig.getPlatformById(anyLong())).thenReturn(PlatformType.GATEWAY);

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.GENERATE_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO()).when(TransactionMapper.class);

            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(() -> transactionServiceImpl.doPayment(getTransactionRequestDTO()));
        }
    }

    @Test
    void testDoRefundShouldBeOk() throws BaseException, EntityNotFoundException {
        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.REFUND_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO())
                .when(TransactionMapper.class);
            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(() -> transactionServiceImpl.doRefund(TransactionSamples.getRefundRequestDTO()));
        }
    }

    @Test
    void testUpdateTransactionStatusShouldBeOk() throws BaseException, EntityNotFoundException {
        TransactionStatusPipeDTO pipe = Mockito.spy(TransactionSamples.getTransactionStatusPipeDTO());

        pipe.setTransaction(getTransaction());

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(pipe, StepGroupDefinitions.UPDATE_TRANSACTION_STATUS_STEPS)
            ).then((Answer<Void>) invocation -> null);

            assertAll(() -> transactionServiceImpl.updateTransactionStatus(
                TransactionSamples.getTransactionStatusUpdateRequestDTO())
            );
        }
    }

    @Test
    void testUpdateTransactionStatusJMSShouldBeOk() throws BaseException, EntityNotFoundException {
        JmsTransactionStatusPipeDTO pipe = Mockito.spy(
            TransactionSamples.getJmsTransactionStatusPipeDTO()
        );

        pipe.setTransaction(getTransaction());

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    pipe,
                    StepGroupDefinitions.JMS_UPDATE_TRANSACTION_STATUS_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            assertAll(() -> transactionServiceImpl.updateTransactionStatusJMS(
                pipe.getJmsUpdateTransactionStatusDTO(),
                "{}"
            ));
        }
    }

    @Test
    void testEntityRefundShouldBeOk() throws BaseException, EntityNotFoundException {

        when(transactionsRepository.findAll(any(Specification.class))).thenReturn(
            List.of(getTransaction())
        );
        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.REFUND_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO())
                .when(TransactionMapper.class);
            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(() -> transactionServiceImpl.entityRefund(
                JmsEntityRefundRequestDTO.builder().build()
            ));
        }
    }


    @Test
    void testEntityRefundShouldBeErrorWhenEntityNotFound() throws BaseException, EntityNotFoundException {

        when(transactionsRepository.findAll(any(Specification.class))).thenReturn(
            List.of(getTransaction())
        );

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.REFUND_TRANSACTION_STEPS
                )
            ).thenThrow(PaymentStepException.class);

            assertAll(() -> transactionServiceImpl.entityRefund(
                JmsEntityRefundRequestDTO.builder().build()
            ));
        }
    }


    @Test
    void testEntityRefundShouldBeOkWhenSourceIdNotNull() throws BaseException, EntityNotFoundException {

        when(transactionsRepository.findAll(any(Specification.class))).thenReturn(
            List.of(getTransaction())
        );

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.REFUND_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO())
                .when(TransactionMapper.class);
            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(() -> transactionServiceImpl.entityRefund(
                JmsEntityRefundRequestDTO.builder().entitySourceId(1L).build()
            ));
        }
    }

    @Test
    void testEntityRefundShouldBeOkWhenEntitySourceReferenceNotNull() throws BaseException, EntityNotFoundException {

        when(transactionsRepository.findAll(any(Specification.class))).thenReturn(
            List.of(getTransaction())
        );

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.REFUND_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO())
                .when(TransactionMapper.class);
            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(() -> transactionServiceImpl.entityRefund(
                JmsEntityRefundRequestDTO.builder().entitySourceReference("ABC").build()
            ));
        }
    }

    @Test
    void testEntityRefundShouldBeOkWhenSourceIdNotNullAndEntitySourceReferenceNotNull() throws BaseException, EntityNotFoundException {

        when(transactionsRepository.findAll(any(Specification.class))).thenReturn(
            List.of(getTransaction())
        );

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.REFUND_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO())
                .when(TransactionMapper.class);
            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(() -> transactionServiceImpl.entityRefund(
                JmsEntityRefundRequestDTO.builder().entitySourceId(1L).entitySourceReference("ABC").build()
            ));
        }
    }

    @Test
    void testEntityRefundThrowsBaseException() throws BaseException, EntityNotFoundException {

        when(transactionsRepository.findAll(any(Specification.class))).thenReturn(
            List.of(getTransaction())
        );

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() ->
                StepsControl.validateSteps(
                    TransactionSamples.getTransactionCreatePipeDTO(),
                    StepGroupDefinitions.REFUND_TRANSACTION_STEPS
                )
            ).then((Answer<Void>) invocation -> null);

            doReturn(getTransactionResponseDTO())
                .when(TransactionMapper.class);
            TransactionMapper.getPaymentResponseDTO(any(), any());

            assertAll(() -> transactionServiceImpl.entityRefund(
                JmsEntityRefundRequestDTO.builder().entitySourceReference("ABC").build()
            ));
        }
    }

    @Test
    void testEntityRefundThrowsExceptionInDoRefund() throws BaseException, EntityNotFoundException {

        when(transactionsRepository.findAll(any(Specification.class))).thenReturn(
            List.of(getTransaction())
        );

        try (MockedStatic<StepsControl> stepControlStaticMock = mockStatic(StepsControl.class)) {
            stepControlStaticMock.when(() -> StepsControl.validateSteps(any(), any()))
                .thenThrow(new PaymentStepException("BaseException"));

            assertAll(() -> transactionServiceImpl.entityRefund(
                JmsEntityRefundRequestDTO.builder().entitySourceReference("ABC").build()
            ));
        }
    }

    @Test
    public void getTransactionsByEntityEntityReference() throws BaseException, EntityNotFoundException {
        when(transactionsRepository.findAll((Specification<Transaction>) any()))
            .thenReturn(TransactionSamples.getTransactionList());

        var listTransactionEntity=
            transactionServiceImpl.getTransactionsByEntityInfo(1L,null);

        verify(transactionsRepository,times(1))
            .findAll((Specification<Transaction>) any());

        assertNotNull(listTransactionEntity, "Not null");
        assertEquals(2, listTransactionEntity.size(), "Equals");
        assertEquals("123", listTransactionEntity.get(0).getUuid(), "Equals");
    }

    @Test
    public void getTransactionsByEntityEntityReferenceNonNull() throws BaseException, EntityNotFoundException {
        when(transactionsRepository.findAll((Specification<Transaction>) any()))
            .thenReturn(TransactionSamples.getTransactionList());

        var listTransactionEntity=
            transactionServiceImpl.getTransactionsByEntityInfo(1L, "1");

        verify(transactionsRepository,times(1))
            .findAll((Specification<Transaction>) any());
        assertNotNull(listTransactionEntity, "Not null");
        assertEquals(2, listTransactionEntity.size(), "Equals");
        assertEquals("123", listTransactionEntity.get(0).getUuid(), "Equals");
    }


    @Test
    public void getTransactionsByEntityEntityReferenceNotNull() throws BaseException, EntityNotFoundException {
        when(transactionsRepository.findAll((Specification<Transaction>) any()))
            .thenReturn(TransactionSamples.getTransactionList());

        var listTransactionEntity=
            transactionServiceImpl.getTransactionsByEntityInfo(null, "1");

        verify(transactionsRepository,times(1))
            .findAll((Specification<Transaction>) any());

        assertNotNull(listTransactionEntity, "Not null");
        assertEquals(2, listTransactionEntity.size(), "Equals");
        assertEquals("123", listTransactionEntity.get(0).getUuid(), "Equals");
    }

    @Test
    public void getTransactionsByEntityEntityReferenceNull() throws BaseException, EntityNotFoundException {
        when(transactionsRepository.findAll(any(Specification.class)))
            .thenReturn(TransactionSamples.getTransactionList());

        var listTransactionEntity=
            transactionServiceImpl.getTransactionsByEntityInfo(null,null);

        verify(transactionsRepository,times(1))
            .findAll((Specification<Transaction>) any());

        assertNotNull(listTransactionEntity, "Not null");
        assertEquals(2, listTransactionEntity.size(), "Equals");
        assertEquals("123", listTransactionEntity.get(0).getUuid(), "Equals");
    }

    @Test
    public void getTransactionsByEntityEntitySourceId() throws BaseException, EntityNotFoundException {
        when(transactionsRepository.findAll(any(Specification.class)))
            .thenReturn(TransactionSamples.getTransactionList());

        var listTransactionEntity=
            transactionServiceImpl.getTransactionsByEntityInfo(1L,null);
        verify(transactionsRepository,times(1))
            .findAll((Specification<Transaction>) any());

        assertNotNull(listTransactionEntity, "Not null");
        assertEquals(2, listTransactionEntity.size(), "Equals");
        assertEquals("123", listTransactionEntity.get(0).getUuid(), "Equals");
    }

    @Test
    public void testGetTransactionsByEntityUuidNotNull() throws BaseException, EntityNotFoundException {
        when(transactionsRepository.findAll( any(Specification.class)))
            .thenReturn(TransactionSamples.getTransactionList());

        var listTransactionEntity=
            transactionServiceImpl.getTransactionsByEntityInfo(null,null, "any");

        verify(transactionsRepository,times(1))
            .findAll((Specification<Transaction>) any());

        assertNotNull(listTransactionEntity, "Not null");
        assertEquals(2, listTransactionEntity.size(), "Equals");
        assertEquals("123", listTransactionEntity.get(0).getUuid(), "Equals");
    }
}