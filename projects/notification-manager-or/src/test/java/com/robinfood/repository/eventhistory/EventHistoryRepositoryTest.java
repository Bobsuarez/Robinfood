package com.robinfood.repository.eventhistory;

import com.robinfood.entities.EventHistoryEntity;
import com.robinfood.network.http.api.RoutingIntegrationBcAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class EventHistoryRepositoryTest {

    @Mock
    private RoutingIntegrationBcAPI routingIntegrationBcAPI;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_saveEventHistory_Should_ReturnEventHistoryEntity_When_InvokeTheRepository() {

        doNothing().when(routingIntegrationBcAPI).saveEventHistory(any(EventHistoryEntity.class), anyString());

        EventHistoryRepository eventHistoryRepository = new EventHistoryRepository(routingIntegrationBcAPI);

        eventHistoryRepository.saveEventHistory(EventHistoryEntity.builder().build(), "token");

        verify(routingIntegrationBcAPI, Mockito.times(1))
                .saveEventHistory(any(EventHistoryEntity.class), anyString());
    }

    @Test
    void test_EventHistoryRepository_Should_BuildConstructor_When_MethodInvoke() {

        EventHistoryRepository eventHistoryRepository = new EventHistoryRepository();

        Assertions.assertNotNull(eventHistoryRepository);
    }
}