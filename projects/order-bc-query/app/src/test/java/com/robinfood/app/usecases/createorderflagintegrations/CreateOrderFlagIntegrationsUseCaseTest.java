package com.robinfood.app.usecases.createorderflagintegrations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.request.order.OrderFlagIntegrationDTO;
import com.robinfood.core.entities.OrderFlagIntegrationEntity;
import com.robinfood.repository.orderflagintegration.IOrderFlagIntegrationRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFlagIntegrationsUseCaseTest {

    @Mock
    private IOrderFlagIntegrationRepository mockOrderFlagIntegrationRepository;

    @InjectMocks
    private CreateOrderFlagIntegrationsUseCase createOrderFlagIntegrationsUseCase;

    private final OrderFlagIntegrationEntity orderFlagIntegrationEntity = new OrderFlagIntegrationEntity(
        null,
        1L,
        1L
    );

    private final OrderFlagIntegrationDTO orderFlagIntegrationDTO = new OrderFlagIntegrationDTO(
        1L,
        1L
    );

    @Test
    void test_CreateOrderFlagIntegration_When_Save_Success() {

        when(mockOrderFlagIntegrationRepository.saveAll(Arrays.asList(orderFlagIntegrationEntity)))
            .thenReturn(Arrays.asList(orderFlagIntegrationEntity));

        final Boolean result = createOrderFlagIntegrationsUseCase
            .invoke(Arrays.asList(orderFlagIntegrationDTO))
            .join();

        verify(mockOrderFlagIntegrationRepository)
            .saveAll(Arrays.asList(orderFlagIntegrationEntity));

        assertTrue(result);
    }
}
