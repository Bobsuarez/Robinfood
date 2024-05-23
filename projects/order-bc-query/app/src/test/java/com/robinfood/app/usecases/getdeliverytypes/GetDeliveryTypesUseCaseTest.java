package com.robinfood.app.usecases.getdeliverytypes;

import com.robinfood.core.dtos.DeliveryTypeDTO;
import com.robinfood.core.entities.DeliveryTypeEntity;
import com.robinfood.repository.deliverytype.IDeliveryTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetDeliveryTypesUseCaseTest {

    @Mock
    private IDeliveryTypeRepository deliveryTypeRepository;

    @InjectMocks
    private GetDeliveryTypesUseCase getDeliveryTypesUseCase;

    private final List<DeliveryTypeEntity> deliveryTypeEntities = new ArrayList<>(Arrays.asList(
            new DeliveryTypeEntity(
                    "description1",
                    null,
                    1L,
                    false,
                    true,
                    false,
                    "delivery",
                    null
            ),
            new DeliveryTypeEntity(
                    "description2",
                    null,
                    2L,
                    true,
                    false,
                    false,
                    "integration",
                    null
            ),
            new DeliveryTypeEntity(
                    "description3",
                    null,
                    3L,
                    false,
                    false,
                    true,
                    "take_away",
                    null
            )
    ));

    private final List<DeliveryTypeDTO> deliveryTypeDTOS = new ArrayList<>(Arrays.asList(
            DeliveryTypeDTO.builder()
                    .description("description1")
                    .id(1L)
                    .isIntegration(false)
                    .isInternalDelivery(true)
                    .isOnPremise(false)
                    .name("delivery")
                    .build(),
            DeliveryTypeDTO.builder()
                    .description("description2")
                    .id(2L)
                    .isIntegration(true)
                    .isInternalDelivery(false)
                    .isOnPremise(false)
                    .name("integration")
                    .build(),
            DeliveryTypeDTO.builder()
                    .description("description3")
                    .id(3L)
                    .isIntegration(false)
                    .isInternalDelivery(false)
                    .isOnPremise(true)
                    .name("take_away")
                    .build()
    ));

    @Test
    void test_GetDeliveryTypes_When_Requested() {
        when(deliveryTypeRepository.findAll()).thenReturn(deliveryTypeEntities);

        final List<DeliveryTypeDTO> result = getDeliveryTypesUseCase.invoke();

        assertEquals(deliveryTypeDTOS, result);
    }
}
