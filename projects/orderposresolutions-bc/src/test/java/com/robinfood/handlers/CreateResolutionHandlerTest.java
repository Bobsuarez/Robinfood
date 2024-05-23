package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.dtos.v1.request.StoreResolutionDTO;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.v1.response.ResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.mocks.dtos.v1.request.APIGatewayProxyRequestEventMock;
import com.robinfood.mocks.dtos.v1.request.StoreResolutionDTOMock;
import com.robinfood.usecases.saveresolutionsbystore.ISaveResolutionsByStoreUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;

class CreateResolutionHandlerTest {

    @Mock
    private ISaveResolutionsByStoreUseCase saveResolutionsByStoreUseCase;

    @Mock
    private Context context;

    @InjectMocks
    private CreateResolutionHandler createResolutionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_HandleRequest_Should_When_Success() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final String body = ObjectMapperSingletonUtil.objectToJson(StoreResolutionDTOMock.build());

        doNothing().when(saveResolutionsByStoreUseCase).invoke(any(StoreResolutionDTO.class));

        final ApiGatewayResponseDTO response = createResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithBody(body), context
        );

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_When_StoreId_IsNull() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final String body = ObjectMapperSingletonUtil.objectToJson(StoreResolutionDTOMock.buildWithStoreIdIsNull());

        doNothing().when(saveResolutionsByStoreUseCase).invoke(any(StoreResolutionDTO.class));

        final ApiGatewayResponseDTO apiGatewayResponse = createResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithBody(body), null
        );

        final ResponseDTO response = ObjectMapperSingletonUtil.jsonToClass(apiGatewayResponse.getBody(),
                ResponseDTO.class);

        assertEquals(response.getCode(), HttpStatus.SC_CREATED);
        clearAllCaches();
    }
}