package com.robinfood.app.usecases.getpickuptime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.domain.PickupTimeMock;
import com.robinfood.repository.pickuptime.IPickupTimeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetPickupTimeUseCaseTest {

    @Mock
    private IPickupTimeRepository pickupTimeRepository;

    @InjectMocks
    private GetPickupTimeUseCase useCase;

    @Test
    void given_transaction_then_return_pickup_time() {
        // Arrange
        var transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;
        var pickupTime = PickupTimeMock.build();

        when(pickupTimeRepository.getByTransaction(anyString(), any())).thenReturn(pickupTime);

        // Act
        var response = useCase.invoke("token", transactionRequest);

        // Assert
        assertEquals(pickupTime.getStores().get(0).getId(), response.getStores().get(0).getId());
        assertEquals(pickupTime.getStores().get(0).getPickupTime(),
            response.getStores().get(0).getPickupTime());
    }

}
