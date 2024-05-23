package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.v1.response.ResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.mocks.dtos.v1.request.APIGatewayProxyRequestEventMock;
import com.robinfood.mocks.dtos.v1.request.ResolutionDTOMock;
import com.robinfood.usecases.updateresolutionsbyposid.IUpdateResolutionsByPosIdUseCase;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

public class UpdateResolutionWithPosHandlerTest {

    @Mock
    private Context context;

    @Mock
    private IUpdateResolutionsByPosIdUseCase updateResolutionsByPosIdUseCase;

    @InjectMocks
    private UpdateResolutionWithPosHandler updateResolutionWithPosHandler;

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
        UpdateResolutionWithPosHandler updateResolutionWithPosHandler = new UpdateResolutionWithPosHandler();

        assertNotNull(updateResolutionWithPosHandler);
    }

    @Test
    void test_HandleRequestUpdateResolutionByPosId_When_Success() {

        APIGatewayProxyRequestEvent event = buildParameters();

        doNothing().when(updateResolutionsByPosIdUseCase).invoke(anyLong(), anyLong());

        final ApiGatewayResponseDTO response = updateResolutionWithPosHandler.handleRequest(event, context);

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_ACCEPTED);
    }

    @Test
    void test_HandleRequestUpdateResolutionByPosId_When_ResolutionId_Is_Null_Error() {

        APIGatewayProxyRequestEvent event = buildParameters();
        event.getPathParameters().put("id", null);
        event.getPathParameters().put("posId", "123");

        doNothing().when(updateResolutionsByPosIdUseCase).invoke(anyLong(), anyLong());

        final ApiGatewayResponseDTO apiGatewayResponse = updateResolutionWithPosHandler.handleRequest(event, context);

        final ResponseDTO response = ObjectMapperSingletonUtil.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        assertEquals(response.getError(), Boolean.TRUE);
    }

    @Test
    void test_HandleRequestUpdateResolutionByPosId_When_PosId_Is_Null_Error() {

        APIGatewayProxyRequestEvent event = buildParameters();
        event.getPathParameters().put("id", "123");
        event.getPathParameters().put("posId", null);

        doNothing().when(updateResolutionsByPosIdUseCase).invoke(anyLong(), anyLong());

        final ApiGatewayResponseDTO apiGatewayResponse = updateResolutionWithPosHandler.handleRequest(event, context);

        final ResponseDTO response = ObjectMapperSingletonUtil.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        assertEquals(response.getError(), Boolean.TRUE);
    }

    private APIGatewayProxyRequestEvent buildParameters(){
        APIGatewayProxyRequestEventMock event = new APIGatewayProxyRequestEventMock();

        String jsonBody = ResolutionDTOMock.buildResolution();
        Map<String, String> pathParameters = new HashMap<>();
        pathParameters.put("id", "123");
        pathParameters.put("posId", "123");

        return event.buildWithBodyAndPathParameters(pathParameters, jsonBody);
    }
}
