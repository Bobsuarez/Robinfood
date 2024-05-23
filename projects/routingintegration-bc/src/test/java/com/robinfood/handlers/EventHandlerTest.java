package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.datamock.request.APIGatewayProxyRequestEventMock;
import com.robinfood.datamock.request.EventRequestDTOMock;
import com.robinfood.datamock.request.FlowEventLogsDTOMock;
import com.robinfood.dtos.createeventflow.request.EventRequestDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.createevent.ICreateEventUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class EventHandlerTest {

    @Mock
    private ICreateEventUseCase createEventUseCase;

    @Mock
    private Context context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Should_HandleRequest_When_Data_Ok() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(createEventUseCase.invoke(any())).thenReturn(FlowEventLogsDTOMock.build());

        final EventHandler eventHandler = new EventHandler(createEventUseCase);

        EventRequestDTO eventRequestDTO = EventRequestDTOMock.build();
        eventRequestDTO.setUuid("uuid");

        final String body = ObjectMapperSingleton.objectToJson(eventRequestDTO);

        eventHandler.handleRequest(APIGatewayProxyRequestEventMock.buildWithBody(body), context);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_When_FieldsValidateException() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final EventHandler eventHandler = new EventHandler(createEventUseCase);

        final String body = ObjectMapperSingleton.objectToJson(EventRequestDTOMock.build());

        final ApiGatewayResponseDTO apiGatewayResponse = eventHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithBody(body), context
        );
        final ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_When_Build_Is_Empty() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        EventHandler eventHandler = new EventHandler();
        Assertions.assertNotNull(eventHandler);
        clearAllCaches();
    }
}
