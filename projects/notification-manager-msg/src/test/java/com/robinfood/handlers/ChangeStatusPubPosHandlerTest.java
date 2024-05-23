package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.datamock.APIGatewayRequestDTOMock;
import com.robinfood.datamock.RequestPublishEventDTOMock;
import com.robinfood.dtos.request.RequestPublishEventDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.queues.publisher.ChangeStatusOrderPublisher;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingletonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

class ChangeStatusPubPosHandlerTest {

    @Mock
    private Context context;

    @InjectMocks
    private ChangeStatusPubPosHandler changeStatusPubPosHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_HandleRequest() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(ChangeStatusOrderPublisher.class);
        doNothing().when(ChangeStatusOrderPublisher.class);
        ChangeStatusOrderPublisher.sendMessage(any(RequestPublishEventDTO.class));

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        final String body = ObjectMapperSingletonUtil.objectToJson(RequestPublishEventDTOMock.getDefault());

        changeStatusPubPosHandler.handleRequest(APIGatewayRequestDTOMock.buildWithBody(body), context);

        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_SecurityException_When_NoFound_Token() {

        mockStatic(ChangeStatusOrderPublisher.class);
        doNothing().when(ChangeStatusOrderPublisher.class);
        ChangeStatusOrderPublisher.sendMessage(any(RequestPublishEventDTO.class));

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        ApiGatewayResponseDTO apiGatewayResponseDTO = changeStatusPubPosHandler.handleRequest(
                APIGatewayRequestDTOMock.getDefault(),
                context
        );

        ResponseDTO response = ObjectMapperSingletonUtil.jsonToClass(apiGatewayResponseDTO.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);

        clearAllCaches();

    }
}
