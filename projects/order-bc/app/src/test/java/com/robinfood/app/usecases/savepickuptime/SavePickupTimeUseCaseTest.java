package com.robinfood.app.usecases.savepickuptime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.robinfood.app.datamocks.domain.PickupTimeMock;
import com.robinfood.app.mappers.PickupTimeMappers;
import com.robinfood.core.entities.PickupTimeEntity;
import com.robinfood.repository.pickuptime.IPickupTimeRepository;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SavePickupTimeUseCaseTest {

    @Mock
    private PickupTimeMappers pickupTimeMappers;

    @Mock
    private IPickupTimeRepository pickupTimeRepository;

    @InjectMocks
    private SavePickupTimeUseCase useCase;

    @Test
    void given_pickup_time_then_return_pickup_time_saved() {
        // Arrange
        var pickupTime = PickupTimeMock.build();

        when(pickupTimeMappers.domainToEntities(any())).thenReturn(
            Collections.singletonList(PickupTimeEntity.builder().build())
        );

        when(pickupTimeRepository.saveAll(any())).thenReturn(
            Collections.singletonList(PickupTimeEntity.builder().build())
        );

        when(pickupTimeMappers.entityToDomain(any())).thenReturn(pickupTime);

        // Act
        var response = useCase.invoke(pickupTime);

        // Assert
        assertNotNull(response);
        assertEquals(pickupTime.getId(), response.get(0).getId());
    }

}
