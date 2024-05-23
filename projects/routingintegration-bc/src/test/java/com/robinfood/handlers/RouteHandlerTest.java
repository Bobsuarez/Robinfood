package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.datamock.request.APIGatewayProxyRequestEventMock;
import com.robinfood.dtos.getrouters.response.FlowResponseDTO;
import com.robinfood.dtos.getrouters.response.RouterResponseDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.exceptions.DataBaseException;
import com.robinfood.exceptions.TokenException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.getrouterbychannelid.IGetRoutersByChannelIdUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_CONNECTION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class RouteHandlerTest {

    @Mock
    private IGetRoutersByChannelIdUseCase getRoutersByChannelIdUseCase;

    @Mock
    private Context context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Should_HandleRequest_When_Data_Ok() throws TokenException {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        when(getRoutersByChannelIdUseCase.invoke(any())).thenReturn(
                RouterResponseDTO.builder()
                        .flowResponseDTO(FlowResponseDTO.builder().flowId(1L).build())
                        .channelId(1L)
                        .build());

        RoutingHandler routeHandler = new RoutingHandler(getRoutersByChannelIdUseCase);
        routeHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), null);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_NotFound_When_NotData_getFlowResponseDTO() throws TokenException {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        when(getRoutersByChannelIdUseCase.invoke(any())).thenReturn(RouterResponseDTO.builder()
                .flowResponseDTO(FlowResponseDTO.builder().build())
                .channelId(1L)
                .build());

        RoutingHandler routeHandler = new RoutingHandler(getRoutersByChannelIdUseCase);
        routeHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_NotFound_When_NotData_getChannelId() throws TokenException {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        when(getRoutersByChannelIdUseCase.invoke(any())).thenReturn(RouterResponseDTO.builder()
                .flowResponseDTO(FlowResponseDTO.builder().flowId(1L).build())
                .build());

        RoutingHandler routeHandler = new RoutingHandler(getRoutersByChannelIdUseCase);
        routeHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_SecurityException_When_NoFound_Token() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        RoutingHandler routeHandler = new RoutingHandler(getRoutersByChannelIdUseCase);

        ApiGatewayResponseDTO apiGatewayResponse =
                routeHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_Exception_When_AnyError() throws TokenException {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        when(getRoutersByChannelIdUseCase.invoke(any()))
                .thenThrow(new DataBaseException(ResponseMapper
                        .buildWithError(
                                HttpStatusConstant.SC_CONFLICT.getCodeHttp(),
                                ERROR_CONNECTION.getMessage(),
                                DEFAULT_BOOLEAN_TRUE),
                        ERROR_CONNECTION.getMessage())
                );

        RoutingHandler routeHandler = new RoutingHandler(getRoutersByChannelIdUseCase);

        ApiGatewayResponseDTO apiGatewayResponse =
                routeHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);

        ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);
        clearAllCaches();
    }

    @Test
    void test_Invoke_buildRoutingHandler_Should_When_Instance() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        RoutingHandler routeHandler = new RoutingHandler();
        Assert.notNull(routeHandler);
        clearAllCaches();
    }
}
