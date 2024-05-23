package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.mocks.dtos.v1.request.APIGatewayProxyRequestEventMock;
import com.robinfood.mocks.dtos.v1.request.EnableDisableResolutionDTOMock;
import com.robinfood.usecases.enabledisableresolution.IEnabledDisabledResolutionUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

class EnabledDisabledResolutionHandlerTest {

    @Mock
    private IEnabledDisabledResolutionUseCase alternateResolutionUseCase;

    @Mock
    private Context context;

    @InjectMocks
    private EnabledDisabledResolutionHandler alternateResolutionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_HandleRequest_Should_DataOk_When_Success() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final String body = ObjectMapperSingletonUtil.objectToJson(EnableDisableResolutionDTOMock.build());


        final ApiGatewayResponseDTO response = alternateResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithPathParametersWithBody(parametersApi(), body), context
        );

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_ACCEPTED);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_AppException_When_NotFoundStatus() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final ApiGatewayResponseDTO response = alternateResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithPathParameters(parametersApi()),
                context
        );

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_When_BuildConstructor_Should_NotFound() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        EnabledDisabledResolutionHandler alternateResolutionHandler = new EnabledDisabledResolutionHandler();

        final ApiGatewayResponseDTO response = alternateResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithPathParameters(parametersApi()),
                context
        );

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
        clearAllCaches();
    }

    private Map<String, String> parametersApi() {
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("resolutionId", "123456");
        return pathParameters;
    }
}
