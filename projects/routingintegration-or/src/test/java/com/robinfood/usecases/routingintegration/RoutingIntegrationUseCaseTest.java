package com.robinfood.usecases.routingintegration;

import com.robinfood.datamock.entity.ResponseRoutingIntegrationEntityMock;
import com.robinfood.dtos.routinintegration.RoutingIntegrationDTO;
import com.robinfood.repository.routingintegration.IRoutingIntegrationRepository;
import com.robinfood.util.LogsUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class RoutingIntegrationUseCaseTest {

    @Mock
    IRoutingIntegrationRepository routingIntegrationRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_invoke_Should_ReturnResponseRoutingIntegrationEntity_When_InvokeTheUseCase() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), anyString(), eq(1L), eq("uuid "));

        when(routingIntegrationRepository.getChannelIntegration(anyLong(), anyString(), anyString()))
                .thenReturn(ResponseRoutingIntegrationEntityMock.getDefault());

        RoutingIntegrationDTO routingIntegrationDTO = new RoutingIntegrationUseCase(routingIntegrationRepository)
                .invoke(1L, "token", "uuid");

        Assertions.assertNotNull(routingIntegrationDTO);

        clearAllCaches();
    }

}
