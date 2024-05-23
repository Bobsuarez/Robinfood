package com.robinfood.app.usecases.getorderintegration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.robinfood.core.dtos.OrderIntegrationDTO;
import com.robinfood.core.entities.OrderIntegrationEntity;
import com.robinfood.repository.orderintegration.IOrderIntegrationRepository;
import java.sql.Date;
import java.sql.Time;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetOrderIntegrationTest {

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
    @Mock
    private IOrderIntegrationRepository mockOrderIntegrationRepository;
    @InjectMocks
    private GetOrderIntegrationUseCase getOrderIntegrationUseCase;

    @Test
    void test_get_order_integration() {
        Mockito.when(mockOrderIntegrationRepository.findById(1L))
            .thenReturn(java.util.Optional.of(orderIntegrationEntity));

        final OrderIntegrationDTO result = getOrderIntegrationUseCase.invoke(1L);

        assertEquals(orderIntegrationDTO, result);
    }
}
