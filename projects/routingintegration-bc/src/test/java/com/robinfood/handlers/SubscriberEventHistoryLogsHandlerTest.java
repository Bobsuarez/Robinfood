package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.datamock.request.APIGatewayProxyRequestEventMock;
import com.robinfood.datamock.request.SubscriberEventHistoryLogsRequestDTOMock;
import com.robinfood.datamock.response.SubscriberEventHistoryLogsResponseDTOMock;
import com.robinfood.dtos.createsubscribereventhistorylogs.request.SubscriberEventHistoryLogsRequestDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.createsubscribereventhistorylogs.ICreateSubscriberEventHistoryLogsUseCase;
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

class SubscriberEventHistoryLogsHandlerTest {

    @Mock
    private ICreateSubscriberEventHistoryLogsUseCase createSubscriberEventHistoryLogsUseCase;

    @Mock
    private Context context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_HandleRequest_Success() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(createSubscriberEventHistoryLogsUseCase.invoke(any())).thenReturn(
                SubscriberEventHistoryLogsResponseDTOMock.build()
        );

        final SubscriberEventHistoryLogsHandler subscriberEventHistoryLogsHandler =
                new SubscriberEventHistoryLogsHandler(createSubscriberEventHistoryLogsUseCase);

        SubscriberEventHistoryLogsRequestDTO eventHistoryLogsRequestDTO =
                SubscriberEventHistoryLogsRequestDTOMock.build();

        eventHistoryLogsRequestDTO.setUuid("uuid");

        final String body = ObjectMapperSingleton.objectToJson(eventHistoryLogsRequestDTO);

        subscriberEventHistoryLogsHandler
                .handleRequest(APIGatewayProxyRequestEventMock.buildWithBody(body), context);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_When_ConstraintViolationException() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(createSubscriberEventHistoryLogsUseCase.invoke(any())).thenReturn(
                SubscriberEventHistoryLogsResponseDTOMock.build()
        );

        final SubscriberEventHistoryLogsHandler subscriberEventHistoryLogsHandler =
                new SubscriberEventHistoryLogsHandler(createSubscriberEventHistoryLogsUseCase);

        final String body = ObjectMapperSingleton.objectToJson(SubscriberEventHistoryLogsRequestDTOMock.build());

        final ApiGatewayResponseDTO apiGatewayResponse = subscriberEventHistoryLogsHandler
                .handleRequest(APIGatewayProxyRequestEventMock.buildWithBody(body), context);

        final ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_When_Build_Empty() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        SubscriberEventHistoryLogsHandler eventHistoryLogsHandler = new SubscriberEventHistoryLogsHandler();
        Assertions.assertNotNull(eventHistoryLogsHandler);
        clearAllCaches();
    }
}
