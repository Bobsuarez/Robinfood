package com.robinfood;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.datamock.APIGatewayProxyRequestEventDTOMock;
import com.robinfood.datamock.RoutingIntegrationDTOMock;
import com.robinfood.dtos.request.RequestChangeOrderStatusDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.dtos.routinintegration.RoutingIntegrationDTO;
import com.robinfood.handlers.RoutingIntegrationHandler;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.changeorderstatus.ChangeOrderStatusUseCase;
import com.robinfood.usecases.routingintegration.RoutingIntegrationUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoutingIntegrationHandlerTest {

    @Mock
    private ChangeOrderStatusUseCase changeOrderStatusUseCase;

    @Mock
    private RoutingIntegrationUseCase routingIntegrationUseCase;

    @Mock
    private Context context;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_HandleRequest() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), any());

        when(routingIntegrationUseCase.invoke(anyLong(), anyString(), anyString()))
                .thenReturn(
                        RoutingIntegrationDTOMock.getDefault()
                );


        RoutingIntegrationHandler routingIntegrationHandler =
                new RoutingIntegrationHandler(changeOrderStatusUseCase, routingIntegrationUseCase);

        ApiGatewayResponseDTO apiGatewayResponseDTO =
                routingIntegrationHandler.handleRequest(APIGatewayProxyRequestEventDTOMock.getDefault(), context);

        System.out.println(ObjectMapperSingleton.objectToJson(apiGatewayResponseDTO));

        Assertions.assertEquals(HttpStatusConstant.SC_OK.getCodeHttp(), apiGatewayResponseDTO.getStatusCode());

        clearAllCaches();
    }

    @Test
    void test_HandleRequest_when_orderUuId() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString(), any());

        when(routingIntegrationUseCase.invoke(anyLong(), anyString(), anyString()))
                .thenReturn(
                        RoutingIntegrationDTOMock.getDefault()
                );

        RoutingIntegrationHandler routingIntegrationHandler =
                new RoutingIntegrationHandler(changeOrderStatusUseCase, routingIntegrationUseCase);

        routingIntegrationHandler.handleRequest(APIGatewayProxyRequestEventDTOMock.getDefault(), context);


        verify(routingIntegrationUseCase).invoke(anyLong(), anyString(), anyString());

        RequestChangeOrderStatusDTO requestChangeOrderStatusDTO = RequestChangeOrderStatusDTO.builder()
                .channelId(10L)
                .notes("Se entrega la orden al domicilario")
                .orderId(9087824L)
                .orderUuid("b3ba6d34-f9ba-4483-b648-15f3990d7b4a")
                .origin("Local Server")
                .statusCode("ORDER_PROCESS")
                .statusId(2L)
                .storeId(27L)
                .userId(1L)
                .build();

        verify(changeOrderStatusUseCase).invoke(
                requestChangeOrderStatusDTO,
                "https://lambdachangeorderstatus-bc.muydev.com/api/vi/orders/states/pos/changestate",
                "token"
        );

        clearAllCaches();
    }


    @Test
    void test_handleRequest_Should_SecurityException_When_NoFound_Token() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.error(anyString(), any());

        when(routingIntegrationUseCase.invoke(anyLong(), anyString(), anyString()))
                .thenReturn(
                        RoutingIntegrationDTO.builder().build()
                );

        RoutingIntegrationHandler routingIntegrationHandler =
                new RoutingIntegrationHandler(changeOrderStatusUseCase, routingIntegrationUseCase);

        ApiGatewayResponseDTO apiGatewayResponseDTO = routingIntegrationHandler.handleRequest(
                APIGatewayProxyRequestEventDTOMock.getDefault(),
                context
        );

        ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponseDTO.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);

        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_BuildConstructor_When_Invoke() {
        RoutingIntegrationHandler routingIntegrationHandler = new RoutingIntegrationHandler();
        Assert.notNull(routingIntegrationHandler);
    }

}

