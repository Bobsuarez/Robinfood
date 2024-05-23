package com.robinfood.app.usecases.getpickuptime;

import com.robinfood.app.mocks.TransactionCreationResponseDTOMock;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.domain.PickupTimeMock;
import com.robinfood.app.usecases.savepickuptime.SavePickupTimeUseCase;
import com.robinfood.core.dtos.ConfigTransactionResponseDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.repository.transaction.ITransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavePickupTimeUseCaseTest {

    @Mock
    private IGetPickupTimeUseCase pickupTimeUseCase;

    @Mock
    private ITransactionRepository transactionRepository;

    @InjectMocks
    private SavePickupTimeUseCase savePickupTimeUseCase;

    @Test
    void given_request_with_pickuptime_consumption_in_true() throws Exception {
        // Arrange
        var token = "token";

        when(pickupTimeUseCase.invoke(any(), any())).thenReturn(PickupTimeMock.build());

        when(transactionRepository.getTransactionResponseDTO()).thenReturn(new ConfigTransactionResponseDTO(
            TransactionCreationResponseDTOMock.build()
        ));

        // Act
        final CompletableFuture<TransactionCreationResult> result =
                savePickupTimeUseCase.invoke(token, new TransactionRequestDTOMock().transactionRequestDTO);

        // Assert
        assertTrue(result.get() instanceof TransactionCreationResult.TransactionCreated);
    }

    @Test
    void given_request_with_pickuptime_consumption_in_false() throws Exception {
        // Arrange
        var token = "token";
        var transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;
        transactionRequestDTO.setPickupTimeConsumption(Boolean.FALSE);

        // Act
        var result = savePickupTimeUseCase.invoke(token, transactionRequestDTO)
            .join();

        // Assert
        assertEquals(TransactionCreationResult.StepValidationSuccess.INSTANCE, result);
    }

}
