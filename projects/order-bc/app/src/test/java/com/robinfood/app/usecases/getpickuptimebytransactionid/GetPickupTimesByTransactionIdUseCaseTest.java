package com.robinfood.app.usecases.getpickuptimebytransactionid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.domain.PickupTimeMock;
import com.robinfood.app.mappers.PickupTimeMappers;
import com.robinfood.core.entities.PickupTimeEntity;
import com.robinfood.core.models.domain.PickupTime;
import com.robinfood.repository.pickuptime.IPickupTimeRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetPickupTimesByTransactionIdUseCaseTest {

    @Mock
    private PickupTimeMappers pickupTimeMappers;

    @Mock
    private IPickupTimeRepository pickupTimeRepository;

    @InjectMocks
    private GetPickupTimesByTransactionIdUseCase useCase;

    @Test
    void get_pickup_times_by_transaction_id() {

        // Arrange
        Long transactionId = 1L;

        PickupTime pickupTime = PickupTimeMock.build();

        when(pickupTimeRepository.findByTransactionId(anyLong())).thenReturn(
            Collections.singletonList(PickupTimeEntity.builder().build())
        );

        when(pickupTimeMappers.entityToDomain(any())).thenReturn(pickupTime);

        // Act
        List<PickupTime> pickupTimes = useCase.invoke(transactionId);

        // Assert
        assertEquals(pickupTime.getId(), pickupTimes.get(0).getId());
        assertEquals(pickupTime.getTransactionId(), pickupTimes.get(0).getTransactionId());
    }

}
