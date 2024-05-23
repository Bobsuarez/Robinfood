package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.datamock.request.APIGatewayProxyRequestEventMock;
import com.robinfood.dtos.getconfigsubscribers.reponse.FlowDTO;
import com.robinfood.dtos.getconfigsubscribers.reponse.ResponseConfigSubscribersDTO;
import com.robinfood.dtos.getconfigsubscribers.reponse.SubscribersItemDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.exceptions.DataBaseException;
import com.robinfood.exceptions.TokenException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.getconfigsubscribers.IGetConfigSubscribersUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_CONNECTION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class SubscribersHandlerTest {

    @Mock
    private IGetConfigSubscribersUseCase getConfigSubscribersUseCase;

    @Mock
    private Context context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleRequest() throws TokenException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(getConfigSubscribersUseCase.invoke(any())).thenReturn(
                ResponseConfigSubscribersDTO.builder()
                        .subscribers(List.of(SubscribersItemDTO.builder().name("name").build()))
                        .flow(FlowDTO.builder().flowId(1L).build())
                        .build());

        SubscribersHandler subscribersHandler = new SubscribersHandler(getConfigSubscribersUseCase);
        subscribersHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_NotFound_When_NotData_subscriber() throws TokenException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(getConfigSubscribersUseCase.invoke(any())).thenReturn(
                ResponseConfigSubscribersDTO.builder()
                        .subscribers(Collections.emptyList())
                        .flow(FlowDTO.builder().code("Code").build())
                        .build());

        SubscribersHandler subscribersHandler = new SubscribersHandler(getConfigSubscribersUseCase);
        subscribersHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_NotFound_When_NotData_flow() throws TokenException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(getConfigSubscribersUseCase.invoke(any())).thenReturn(
                ResponseConfigSubscribersDTO.builder()
                        .subscribers(List.of(SubscribersItemDTO.builder().name("name").build()))
                        .flow(FlowDTO.builder().build())
                        .build());

        SubscribersHandler subscribersHandler = new SubscribersHandler(getConfigSubscribersUseCase);
        subscribersHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        clearAllCaches();
    }


    @Test
    void test_handleRequest_Should_SecurityException_When_NoFound_Token() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        SubscribersHandler subscribersHandler = new SubscribersHandler(getConfigSubscribersUseCase);

        ApiGatewayResponseDTO apiGatewayResponse =
                subscribersHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);
        ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);

        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_Exception_When_AnyError() throws TokenException {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        when(getConfigSubscribersUseCase.invoke(any()))
                .thenThrow(new DataBaseException(ResponseMapper
                        .buildWithError(
                                HttpStatusConstant.SC_CONFLICT.getCodeHttp()    ,
                                ERROR_CONNECTION.getMessage(),
                                DEFAULT_BOOLEAN_TRUE),
                        ERROR_CONNECTION.getMessage())
                );

        SubscribersHandler subscribersHandler = new SubscribersHandler(getConfigSubscribersUseCase);

        ApiGatewayResponseDTO apiGatewayResponse =
                subscribersHandler.handleRequest(APIGatewayProxyRequestEventMock.build(), context);

        ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        Assertions.assertEquals(response.getError(), true);
        clearAllCaches();
    }

    @Test
    void test_Invoke_buildSubscribersHandler_Should_When_Instance() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        SubscribersHandler subscribersHandler = new SubscribersHandler();
        Assert.notNull(subscribersHandler);
        clearAllCaches();
    }
}
