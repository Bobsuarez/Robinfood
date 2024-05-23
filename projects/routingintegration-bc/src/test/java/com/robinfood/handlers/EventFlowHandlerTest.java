package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.datamock.request.APIGatewayProxyRequestEventMock;
import com.robinfood.dtos.geteventflow.response.ResponseEventFlowDTO;
import com.robinfood.exceptions.TokenException;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.geteventsflow.IGetEventsFlowUseCase;
import com.robinfood.util.LogsUtil;
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

class EventFlowHandlerTest {
    @Mock
    private IGetEventsFlowUseCase getEventsFlowUseCase;

    @Mock
    private Context context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void test_Should_EventFlowHandlerTest_When_Data_Ok() throws TokenException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(getEventsFlowUseCase.invoke(any())).thenReturn(ResponseEventFlowDTO.builder().id(1L).build());

        EventFlowHandler eventFlowHandler = new EventFlowHandler(getEventsFlowUseCase);
        eventFlowHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_NotFound_When_NotData() throws TokenException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(getEventsFlowUseCase.invoke(any())).thenReturn(ResponseEventFlowDTO.builder().build());

        EventFlowHandler eventFlowHandler = new EventFlowHandler(getEventsFlowUseCase);
        eventFlowHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_SecurityException_When_NoFound_Token() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        EventFlowHandler eventFlowHandler = new EventFlowHandler();
        eventFlowHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }
}