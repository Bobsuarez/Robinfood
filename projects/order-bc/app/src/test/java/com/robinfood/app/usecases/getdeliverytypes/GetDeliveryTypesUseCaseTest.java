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
            new DeliveryTypeDTO(
                    "description1",
                    1L,
                    false,
                    true,
                    false,
                    "delivery"
            ),
            new DeliveryTypeDTO(
                    "description2",
                    2L,
                    true,
                    false,
                    false,
                    "integration"
            ),
            new DeliveryTypeDTO(
                    "description3",
                    3L,
                    false,
                    false,
                    true,
                    "take_away"
            )
    ));

    @Test
    void test_GetDeliveryTypes_When_None_Is_True() {
        when(deliveryTypeRepository.findAll()).thenReturn(deliveryTypeEntities);

        final List<DeliveryTypeDTO> result = getDeliveryTypesUseCase.invoke(false, false, false);

        assertEquals(deliveryTypeDTOS, result);
    }

    @Test
    void test_GetDeliveryTypes_When_Is_Integration_Is_True() {
        when(deliveryTypeRepository.findAll()).thenReturn(deliveryTypeEntities);

        final List<DeliveryTypeDTO> result = getDeliveryTypesUseCase.invoke(true, false, false);

        assertEquals(deliveryTypeDTOS.get(1), result.get(0));
    }

    @Test
    void test_GetDeliveryTypes_When_Is_Internal_Delivery_Is_True() {
        when(deliveryTypeRepository.findAll()).thenReturn(deliveryTypeEntities);

        final List<DeliveryTypeDTO> result = getDeliveryTypesUseCase.invoke(false, true, false);

        assertEquals(deliveryTypeDTOS.get(0), result.get(0));
    }

    @Test
    void test_GetDeliveryTypes_When_Is_On_Premise_Is_True() {
        when(deliveryTypeRepository.findAll()).thenReturn(deliveryTypeEntities);

        final List<DeliveryTypeDTO> result = getDeliveryTypesUseCase.invoke(false, false, true);

        assertEquals(deliveryTypeDTOS.get(2), result.get(0));
    }
}
