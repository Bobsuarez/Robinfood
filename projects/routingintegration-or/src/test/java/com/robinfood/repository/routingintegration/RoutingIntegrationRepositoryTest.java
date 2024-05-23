package com.robinfood.repository.routingintegration;

import com.robinfood.datamock.entity.ResponseRoutingIntegrationEntityMock;
import com.robinfood.entities.api.routingintegration.ResponseRoutingIntegrationEntity;
import com.robinfood.network.api.RoutingIntegrationBcApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RoutingIntegrationRepositoryTest {

    @Mock
    RoutingIntegrationBcApi routingIntegrationBcApi;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_RoutingIntegrationRepository_Should_ReturnEntity_When_InvokeApi() {

        when(routingIntegrationBcApi.getChannelIntegration(anyLong(), anyString(), anyString()))
                .thenReturn(ResponseRoutingIntegrationEntityMock.getDefault());

        RoutingIntegrationRepository routingIntegrationRepository = new RoutingIntegrationRepository(routingIntegrationBcApi);

        ResponseRoutingIntegrationEntity result = routingIntegrationRepository.getChannelIntegration(
                1L,
                "Token",
                "valor"
        );

        assertEquals("ecd3042e-2967-4a2b-b020-a334d758f54c", result.getUuid());
    }
}
