package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.datamock.APIGatewayRequestDTOMock;
import com.robinfood.datamock.RequestChangeStateDTOMock;
import com.robinfood.dtos.request.RequestChangeStateDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.queues.publisher.ChangeStatusOrderPublisher;
import com.robinfood.security.JwtSecurity;
import com.robinfood.usecases.SendMessageUseCase;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingletonUtil;
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

class ChangeOrderStatusHandlerTest {

    @Mock
    private Context context;

    @Mock
    private SendMessageUseCase sendMessageUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_ChangeOrderStatusHandler() {

        mockStatic(JwtSecurity.class);
        doNothing().when(JwtSecurity.class);
        JwtSecurity.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(ChangeStatusOrderPublisher.class);
        doNothing().when(ChangeStatusOrderPublisher.class);
        ChangeStatusOrderPublisher.sendMessage(any(RequestChangeStateDTO.class));

        doNothing().when(sendMessageUseCase).sendMessages(any(RequestChangeStateDTO.class));

        final String body = ObjectMapperSingletonUtil.objectToJson(RequestChangeStateDTOMock.getDefault());

        ChangeOrderStatusHandler changeOrderStatusHandler = new ChangeOrderStatusHandler(sendMessageUseCase);

        changeOrderStatusHandler.handleRequest(APIGatewayRequestDTOMock.buildWithBody(body), context);

        clearAllCaches();

    }

    @Test
    void test_ChangeOrderStatusHandler_Should_SecurityException_When_NoFound_Token() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(ChangeStatusOrderPublisher.class);
        doNothing().when(ChangeStatusOrderPublisher.class);
        ChangeStatusOrderPublisher.sendMessage(any(RequestChangeStateDTO.class));

        final String body = ObjectMapperSingletonUtil.objectToJson(RequestChangeStateDTOMock.getDefault());

        ChangeOrderStatusHandler changeOrderStatusHandler = new ChangeOrderStatusHandler(sendMessageUseCase);

        ApiGatewayResponseDTO responseDTO = changeOrderStatusHandler
                .handleRequest(
                        APIGatewayRequestDTOMock.buildWithBody(body),
                        context
                );

        ResponseDTO response = ObjectMapperSingletonUtil.jsonToClass(responseDTO.getBody(), ResponseDTO.class);

        Assertions.assertEquals(Boolean.TRUE, response.getError());

        clearAllCaches();

    }

    @Test
    void test_ChangeOrderStatusHandler_Should_BuildConstructor_When_MethodInvoke() {

        ChangeOrderStatusHandler changeOrderStatusHandler = new ChangeOrderStatusHandler();
        Assertions.assertNotNull(changeOrderStatusHandler);
    }
}
