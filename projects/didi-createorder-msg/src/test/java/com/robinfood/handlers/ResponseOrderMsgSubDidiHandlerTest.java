package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.robinfood.datamock.OrderToCreateDTOMock;
import com.robinfood.datamock.request.APIGatewayRequestEventMock;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.usecases.processhandler.ProcessHandlerUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingletonUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class ResponseOrderMsgSubDidiHandlerTest {

    @Mock
    private SQSEvent sqsEvent;

    @Mock
    private Context context;

    @Mock
    private ProcessHandlerUseCase processHandlerUseCase;

    @NotNull
    private static SQSEvent.SQSMessage getActiveMQMessage() {
        SQSEvent.SQSMessage activeMQMessage = new SQSEvent.SQSMessage();
        activeMQMessage.setBody(ObjectMapperSingletonUtil.objectToJson(OrderToCreateDTOMock.getDataDefault()));
        return activeMQMessage;
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_ResponseOrderMsgSubDidiHandler_Should_ProcessTheMessage_When_MethodInvoke() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        doNothing().when(processHandlerUseCase).invoke(any(OrderToCreateDTO.class), anyMap());

        SQSEvent.SQSMessage sqsMessage = getActiveMQMessage();
        sqsMessage.setBody(APIGatewayRequestEventMock.getBody());

        Map<String, SQSEvent.MessageAttribute> messageAttributes = new HashMap<>();

        SQSEvent.MessageAttribute messageAttributeCountry = new SQSEvent.MessageAttribute();
        messageAttributeCountry.setStringValue("colombia");

        messageAttributes.put("messageFrom", messageAttributeCountry);

        SQSEvent.MessageAttribute messageAttributeFrom = new SQSEvent.MessageAttribute();
        messageAttributeFrom.setStringValue("DIDI");
        messageAttributes.put("messageCountry", messageAttributeFrom);

        sqsMessage.setMessageAttributes(messageAttributes);

        List<SQSEvent.SQSMessage> sqsMessages = new ArrayList<>();

        sqsMessages.add(sqsMessage);

        when(sqsEvent.getRecords()).thenReturn(sqsMessages);

        ResponseOrderMsgSubDidiHandler orderMsgSubDidiHandler =
                new ResponseOrderMsgSubDidiHandler(processHandlerUseCase);

        orderMsgSubDidiHandler.handleRequest(sqsEvent, context);

        Mockito.verify(processHandlerUseCase).invoke(any(), anyMap());
        clearAllCaches();
    }

    @Test
    void test_ResponseOrderMsgSubDidiHandler_Should_ShowException_When_MethodInvoke() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        doNothing().when(processHandlerUseCase).invoke(any(OrderToCreateDTO.class), anyMap());

        ResponseOrderMsgSubDidiHandler orderMsgSubDidiHandler =
                new ResponseOrderMsgSubDidiHandler(processHandlerUseCase);

        orderMsgSubDidiHandler.handleRequest(sqsEvent, context);

        Assertions.assertNotNull(orderMsgSubDidiHandler);
        clearAllCaches();
    }

    @Test
    void test_ResponseOrderMsgSubDidiHandler_Should_BuildConstructor_When_MethodInvoke() {

        ResponseOrderMsgSubDidiHandler orderMsgSubDidiHandler =
                new ResponseOrderMsgSubDidiHandler();

        Assertions.assertNotNull(orderMsgSubDidiHandler);
        clearAllCaches();
    }
}
