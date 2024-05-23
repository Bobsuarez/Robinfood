package com.robinfood.app.usecases.createorderintegrations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.request.order.OrderIntegrationDTO;
import com.robinfood.core.entities.OrderIntegrationEntity;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateOrderIntegrationUseCaseTest {

    @Mock
    private IOrderIntegrationRepository orderIntegrationRepository;

    @InjectMocks
    private CreateOrderIntegrationUseCase createOrderIntegrationUseCase;

    private final OrderIntegrationEntity orderIntegrationEntity = new OrderIntegrationEntity(
        "carrera",
        "edificio",
        Date.valueOf("2020-10-23"),
        Time.valueOf("11:10:00"),
        "123",
        null,
        1L,
        "MUY",
        "dSW123",
        "Test",
        102L,
        1L,
        "Caja",
        1,
        "Efectivo",
        1L,
        "MUY 79",
        8900.0,
        100.0,
        0.0,
        1.0,
        null,
        "test 1",
        "300201"
    );

    private final OrderIntegrationDTO orderIntegrationDTO = new OrderIntegrationDTO(
        "carrera",
        "edificio",
        Date.valueOf("2020-10-23"),
        Time.valueOf("11:10:00"),
        1L,
        "MUY",
        "123",
        "dSW123",
        "Test",
        102L,
        1L,
        "Caja",
        1,
        "Efectivo",
        1L,
        "MUY 79",
        8900.0,
        100.0,
        0.0,
        1.0,
        "test 1",
        "300201"
    );

    @Test
    void test_CreateOrderIntegration_When_Save_Success() {

        when(orderIntegrationRepository.saveAll(Arrays.asList(orderIntegrationEntity)))
            .thenReturn(Arrays.asList(orderIntegrationEntity));

        final Boolean result = createOrderIntegrationUseCase
            .invoke(Arrays.asList(orderIntegrationDTO))
            .join();

        verify(orderIntegrationRepository)
            .saveAll(Arrays.asList(orderIntegrationEntity));

        assertTrue(result);
    }
}
