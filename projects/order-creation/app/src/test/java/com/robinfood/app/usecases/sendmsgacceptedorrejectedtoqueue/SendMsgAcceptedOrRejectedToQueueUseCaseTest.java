package com.robinfood.app.usecases.sendmsgacceptedorrejectedtoqueue;

import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.queue.paymentmethod.TransactionDTOMock;
import com.robinfood.app.usecases.gettemporarytransaction.IGetTemporaryTransactionUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.paymentmethodpaiddto.PaymentMethodRefundResponseDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionStatusDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.paymentmethodpaidentities.PaymentMethodRefundEntity;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.models.domain.Token;
import com.robinfood.repository.queue.activemq.IProducerRepository;
import com.robinfood.repository.queue.activemq.paymentmethodsrefunds.IPaymentMethodRefundsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SendMsgAcceptedOrRejectedToQueueUseCaseTest {

    private final String TOKEN = "token";

    @Mock
    private IGetTemporaryTransactionUseCase getTemporaryTransactionUseCase;

    @Mock
    private IInvokerCommand invokerCommand;

    @Mock
    private IProducerRepository producerRepository;

    @Mock
    private IPaymentMethodRefundsRepository paymentMethodRefundsRepository;

    @InjectMocks
    private SendMsgAcceptedOrRejectedToQueueUseCase sendMsgAcceptedOrRejectedToQueueUseCase;

    private TransactionDTO transactionDTO;

    @Mock
    IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @BeforeEach
    void setUp() {
        transactionDTO = TransactionDTOMock.build();
    }

    @Test
    void test_SendMsgAcceptedOrRejectedToQueue_When_PaymentStatus_Is_Not_Null() {

        // Arrange
        final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(Token.builder()
                .accessToken(TOKEN)
                .build());

        when(getTemporaryTransactionUseCase.invoke(anyString())).thenReturn(transactionRequestDTO);
        doNothing().when(invokerCommand).group(anyString(), eq(transactionRequestDTO));

        // Act
        sendMsgAcceptedOrRejectedToQueueUseCase.invoke(transactionDTO);

        // Assert
        verify(getTemporaryTransactionUseCase).invoke(anyString());
        verify(producerRepository).sendChangeStatusMessage(any());
    }

    @Test
    void test_SendMsgAcceptedOrRejectedToQueue_When_PaymentStatus_Is_Rejected() {

        // Arrange
        transactionDTO.setTransactionStatus(new TransactionStatusDTO(
                3L,
                "2022-01-11T22:40:39.780379Z",
                "Rejected")
        );

        PaymentMethodRefundEntity paymentMethodRefundEntity = PaymentMethodRefundEntity.builder()
                .entitySourceId(3L).entityId(1L).entitySourceReference("refernce").reason("Canceled").build();

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(Token.builder()
                .accessToken(TOKEN)
                .build());

        final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;
        when(getTemporaryTransactionUseCase.invoke(anyString())).thenReturn(transactionRequestDTO);

        when(paymentMethodRefundsRepository.sendRefundMessage(TOKEN, paymentMethodRefundEntity)).thenReturn(
                CompletableFuture.completedFuture(PaymentMethodRefundResponseDTO.builder().code(200).message("sucessfully").build())
        );;

        // Act
        sendMsgAcceptedOrRejectedToQueueUseCase.invoke(transactionDTO);

        // Assert
        verify(producerRepository).sendChangeStatusMessage(any());


    }

    @Test
    void test_SendMsgAcceptedOrRejectedToQueue_When_PaymentStatus_Is_NUll() {

        // Arrange
        transactionDTO.setTransactionStatus(new TransactionStatusDTO(
                5L,
                "2022-01-11T22:40:39.780379Z",
                "Unknown")
        );

        // Act - Assert
        assertThrows(TransactionCreationException.class, () -> sendMsgAcceptedOrRejectedToQueueUseCase.invoke(transactionDTO));
        verify(producerRepository, times(0)).sendChangeStatusMessage(any());
    }

    @Test
    void test_SendMsgAcceptedOrRejectedToQueue_When_PaymentStatus_Is_Refund() {

        // Arrange
        transactionDTO.setTransactionStatus(new TransactionStatusDTO(
                4L,
                "2022-01-11T22:40:39.780379Z",
                "Refunded")
        );

        // Act - Assert
        verify(producerRepository, times(0)).sendChangeStatusMessage(any());
    }

    @Test
    void test_SendMsgAcceptedOrRejectedToQueue_When_PaymentStatus_Is_Pending_And_Entity_OU() {

        // Arrange
        transactionDTO.getEntity().setId(1L);
        transactionDTO.setTransactionStatus(new TransactionStatusDTO(
                2L,
                "2022-01-11T22:40:39.780379Z",
                "Pending")
        );

        // Act - Assert
        sendMsgAcceptedOrRejectedToQueueUseCase.invoke(transactionDTO);

        verify(producerRepository, times(0)).sendChangeStatusMessage(any());
    }

    @Test
    void test_SendMsgAcceptedOrRejectedToQueue_When_PaymentStatus_Is_Refund_And_Entity_Not_OU() {

        // Arrange
        transactionDTO.getEntity().setId(2L);
        transactionDTO.setTransactionStatus(new TransactionStatusDTO(
                4L,
                "2022-01-11T22:40:39.780379Z",
                "Refunded")
        );

        // Act - Assert
        sendMsgAcceptedOrRejectedToQueueUseCase.invoke(transactionDTO);

        verify(producerRepository, times(0)).sendChangeStatusMessage(any());
    }
}