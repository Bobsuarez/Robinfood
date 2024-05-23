package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.datamock.APIGatewayProxyRequestEventDTOMock;
import com.robinfood.datamock.TransactionResponseDTOMock;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.processconnectortosimba.IProcessConnectorToSimbaUseCase;
import com.robinfood.util.LogsUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
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
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class SendOrderToSimbaHandlerTest {

    @Mock
    private Context context;

    @Mock
    private IProcessConnectorToSimbaUseCase sendOrderToSimbaUseCase;


    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), any());
    }

    @AfterEach
    public void setupAfter() {
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Accepted() {

        when(sendOrderToSimbaUseCase.invoke(any(TransactionRequestDTO.class), anyString()))
                .thenReturn(
                        TransactionResponseDTOMock.getDefault()
                );

        SendOrderToSimbaHandler sendOrderToSimbaHandler =
                new SendOrderToSimbaHandler(sendOrderToSimbaUseCase);

        ApiGatewayResponseDTO apiGatewayResponseDTO =
                sendOrderToSimbaHandler.handleRequest(APIGatewayProxyRequestEventDTOMock.buildWithBody(), context);

        Assertions.assertEquals(apiGatewayResponseDTO.getStatusCode(), 202);
    }

    @Test
    void test_HandleRequest_Should_AppException_When_NoFoundOrders() {

        when(sendOrderToSimbaUseCase.invoke(any(TransactionRequestDTO.class), anyString()))
                .thenReturn(
                        TransactionResponseDTOMock.getDefault()
                );

        SendOrderToSimbaHandler sendOrderToSimbaHandler =
                new SendOrderToSimbaHandler(sendOrderToSimbaUseCase);

        ApiGatewayResponseDTO apiGatewayResponseDTO =
                sendOrderToSimbaHandler.handleRequest(APIGatewayProxyRequestEventDTOMock.buildWithBodyWithOutOrders(), context);

        Assertions.assertEquals(apiGatewayResponseDTO.getStatusCode(), 404);
    }

    @Test
    void test_HandleRequest_Should_BuildDefault_When_NoFoundThirdParty() {

        when(sendOrderToSimbaUseCase.invoke(any(TransactionRequestDTO.class), anyString()))
                .thenReturn(
                        TransactionResponseDTOMock.getDefault()
                );

        SendOrderToSimbaHandler sendOrderToSimbaHandler =
                new SendOrderToSimbaHandler(sendOrderToSimbaUseCase);

        Assertions.assertThrows(NumberFormatException.class, () -> {
            sendOrderToSimbaHandler.handleRequest(APIGatewayProxyRequestEventDTOMock.buildWithBodyWithOutThirdParty(), context);

        });
    }

    @Test
    void test_Invoke_BuildSendOrderToSimbaHandler_Should_When_Instance() {

        SendOrderToSimbaHandler sendOrderToSimbaHandler =
                new SendOrderToSimbaHandler();

        Assert.notNull(sendOrderToSimbaHandler);
    }
}