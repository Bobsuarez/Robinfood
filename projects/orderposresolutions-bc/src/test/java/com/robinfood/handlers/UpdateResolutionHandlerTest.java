package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.v1.request.ResolutionUpdateDTO;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.v1.response.ResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.mocks.dtos.v1.request.APIGatewayProxyRequestEventMock;
import com.robinfood.mocks.dtos.v1.request.ResolutionDTOMock;
import com.robinfood.usecases.updateresolutionsbyresolutionid.IUpdateResolutionsByResolutionIdUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

public class UpdateResolutionHandlerTest {

    @Mock
    private Context context;

    @Mock
    private IUpdateResolutionsByResolutionIdUseCase updateResolutionsByResolutionIdUseCase;

    @InjectMocks
    private UpdateResolutionHandler updateResolutionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());
    }

    @AfterEach
    void tearDown() {
        clearAllCaches();
    }

    @Test
    void testConstructorWithNoArgs() {
        UpdateResolutionHandler updateResolutionHandler = new UpdateResolutionHandler();

        assertNotNull(updateResolutionHandler);
    }

    @Test
    void test_HandleRequestUpdateResolution_When_Success() {

        APIGatewayProxyRequestEvent event = buildParameters();

        doNothing().when(updateResolutionsByResolutionIdUseCase).invoke(anyLong(), any(ResolutionUpdateDTO.class));

        final ApiGatewayResponseDTO response = updateResolutionHandler.handleRequest(event, context);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_ACCEPTED);
    }

    @Test
    void test_HandleRequestUpdateResolution_When_Body_is_null_Error() {

        APIGatewayProxyRequestEvent event = buildParameters();
        event.setBody(null);

        doNothing().when(updateResolutionsByResolutionIdUseCase).invoke(anyLong(), any(ResolutionUpdateDTO.class));

        final ApiGatewayResponseDTO apiGatewayResponse = updateResolutionHandler.handleRequest(event, null);

        final ResponseDTO response = ObjectMapperSingletonUtil.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        assertEquals(response.getError(), Boolean.TRUE);
    }

    @Test
    void test_HandleRequestUpdateResolution_When_ResolutionId_is_null_Error() {

        APIGatewayProxyRequestEvent event = buildParameters();
        event.getPathParameters().put("resolutionId", null);

        doNothing().when(updateResolutionsByResolutionIdUseCase).invoke(anyLong(), any(ResolutionUpdateDTO.class));

        final ApiGatewayResponseDTO apiGatewayResponse = updateResolutionHandler.handleRequest(event, null);

        final ResponseDTO response = ObjectMapperSingletonUtil.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        assertEquals(response.getError(), Boolean.TRUE);
    }

    private APIGatewayProxyRequestEvent buildParameters(){
        APIGatewayProxyRequestEventMock event = new APIGatewayProxyRequestEventMock();

        String jsonBody = ResolutionDTOMock.buildResolution();
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("resolutionId", "123");

        return event.buildWithBodyAndPathParameters(pathParameters, jsonBody);
    }
}
