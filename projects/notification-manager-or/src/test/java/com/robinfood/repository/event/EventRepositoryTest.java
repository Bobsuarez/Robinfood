package com.robinfood.repository.event;

import com.robinfood.entities.EventEntity;
import com.robinfood.network.http.api.RoutingIntegrationBcAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventRepositoryTest {

    @Mock
    private RoutingIntegrationBcAPI routingIntegrationBcAPI;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_GetEventByIdAndFlow_Should_ReturnEventEntity_When_InvokeTheRepository() {

        when(routingIntegrationBcAPI.getEventByIdAndFlow(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(EventEntity.builder().build());

        EventRepository eventRepository = new EventRepository(routingIntegrationBcAPI);

        eventRepository.getEventByIdAndFlow("", "", "", "");

        verify(routingIntegrationBcAPI, Mockito.times(1))
                .getEventByIdAndFlow(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void test_SaveEvent_Should_ReturnEventEntity_When_InvokeTheRepository() {
        when(routingIntegrationBcAPI.saveEvent(any(EventEntity.class), anyString()))
                .thenReturn(EventEntity.builder().build());


        EventRepository eventRepository = new EventRepository(routingIntegrationBcAPI);

        eventRepository.saveEvent(EventEntity.builder().build(), "");

        verify(routingIntegrationBcAPI, Mockito.times(1))
                .saveEvent(any(EventEntity.class), anyString());
    }

    @Test
    void test_EventRepository_Should_BuildConstructor_When_MethodInvoke() {

        EventRepository eventRepository = new EventRepository();

        Assertions.assertNotNull(eventRepository);
    }
}