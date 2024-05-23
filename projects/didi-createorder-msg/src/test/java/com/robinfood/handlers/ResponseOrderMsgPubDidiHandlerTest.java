package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.datamock.OrderToCreateDTOMock;
import com.robinfood.datamock.request.APIGatewayRequestEventMock;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.exceptions.DataNotFoundException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.publisher.SendMessageSQS;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingletonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;

class ResponseOrderMsgPubDidiHandlerTest {

    @Mock
    private SendMessageSQS sendMessageSQS;

    @Mock
    private Context context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void test_ResponseOrderMsgPubDidiHandlerTest_When_DataOk_Should_ResponseCodeCreated() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        doNothing().when(sendMessageSQS).invoke(any(), anyString(), anyString());

        ResponseOrderMsgPubDidiHandler msgPubDidiHandler = new ResponseOrderMsgPubDidiHandler(sendMessageSQS);

        String bodyRequest = ObjectMapperSingletonUtil.objectToJson(OrderToCreateDTOMock.getDataDefault());

        ApiGatewayResponseDTO response =
                msgPubDidiHandler.handleRequest(APIGatewayRequestEventMock.buildWithBody(bodyRequest), context);

        Assertions.assertEquals(HttpStatusConstant.SC_CREATED.getCodeHttp(), response.getStatusCode());
        clearAllCaches();
    }

    @Test
    void test_ResponseOrderMsgPubDidiHandlerTest_When_SendMessageSQS_Should_ResponseCodeNotFound() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        doThrow(new DataNotFoundException(ResponseMapper.buildWithError(
                HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                "Error",
                Boolean.TRUE
        ), "Error")).when(sendMessageSQS).invoke(any(), anyString(), anyString());

        ResponseOrderMsgPubDidiHandler msgPubDidiHandler = new ResponseOrderMsgPubDidiHandler(sendMessageSQS);

        String bodyRequest = ObjectMapperSingletonUtil.objectToJson(OrderToCreateDTOMock.getDataDefault());

        ApiGatewayResponseDTO response =
                msgPubDidiHandler.handleRequest(APIGatewayRequestEventMock.buildWithBody(bodyRequest), context);

        Assertions.assertEquals(HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(), response.getStatusCode());
        clearAllCaches();
    }

    @Test
    void test_RResponseOrderMsgPubDidiHandlerTest_Should_BuildConstructor_When_MethodInvoke() {

        ResponseOrderMsgPubDidiHandler msgPubDidiHandler = new ResponseOrderMsgPubDidiHandler();

        Assertions.assertNotNull(msgPubDidiHandler);
        clearAllCaches();
    }

}