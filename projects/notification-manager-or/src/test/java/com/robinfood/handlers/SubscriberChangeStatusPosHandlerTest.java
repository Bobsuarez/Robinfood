package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.ActiveMQEvent;
import com.robinfood.usecases.processhandler.ProcessHandlerUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class SubscriberChangeStatusPosHandlerTest {

    @Mock
    private ActiveMQEvent activeMQEvent;

    @Mock
    private Context context;

    @Mock
    private ProcessHandlerUseCase processHandlerUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_HandlePosRequest_Should_ProcessTheMessage_When_MethodInvoke() {

        doNothing().when(processHandlerUseCase).invoke(any(ActiveMQEvent.class));

        SubscriberChangeStatusPosHandler subscriberChangeStatusPosHandler =
                new SubscriberChangeStatusPosHandler(processHandlerUseCase);

        subscriberChangeStatusPosHandler.handleRequest(activeMQEvent, context);

        Mockito.verify(processHandlerUseCase).invoke(activeMQEvent);
    }

    @Test
    void test_HandleMainRequest_Should_BuildConstructor_When_MethodInvoke() {

        SubscriberChangeStatusPosHandler subscriberChangeStatusPosHandler = new SubscriberChangeStatusPosHandler();

        Assertions.assertNotNull(subscriberChangeStatusPosHandler);
    }
}