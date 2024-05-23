package com.robinfood.repository.subscriber;

import com.robinfood.entities.SubscriberChannelEntity;
import com.robinfood.network.http.api.RoutingIntegrationBcAPI;
import com.robinfood.utils.LogsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SubscriberRepositoryTest {

    @Mock
    private RoutingIntegrationBcAPI routingIntegrationBcAPI;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetSubscriberByChannelIdAndFlow_Should_ReturnSubscriberChannelEntity_When_InvokeTheRepository() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        when(routingIntegrationBcAPI.getSubscriberByChannelIdAndFlow(anyLong(), anyString(), anyString(), anyString()))
                .thenReturn(
                        SubscriberChannelEntity.builder().uuid("1l")
                                .build());

        SubscriberRepository subscriberRepository = new SubscriberRepository(routingIntegrationBcAPI);

        SubscriberChannelEntity result = subscriberRepository.getSubscriberByChannelIdAndFlow(
                1L,
                "",
                "",
                "token"

        );

        verify(routingIntegrationBcAPI, Mockito.times(1))
                .getSubscriberByChannelIdAndFlow(anyLong(), anyString(), anyString(), anyString());

        clearAllCaches();
    }
}