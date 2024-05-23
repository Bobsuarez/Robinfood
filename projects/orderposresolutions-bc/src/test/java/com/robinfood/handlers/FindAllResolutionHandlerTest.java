package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.dtos.v1.request.SearchResolutionsDTO;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.v1.response.searchResolutions.DataResolutionResponseDTO;
import com.robinfood.dtos.v1.response.searchResolutions.PageableDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.mocks.dtos.v1.request.APIGatewayProxyRequestEventMock;
import com.robinfood.mocks.dtos.v1.request.SearchResolutionsDTOMock;
import com.robinfood.usecases.findallresolutions.IFindAllResolutionsUseCase;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.when;

public class FindAllResolutionHandlerTest {

    @Mock
    private IFindAllResolutionsUseCase iFindAllResolutionsUseCase;

    @Mock
    private Context context;

    @InjectMocks
    private FindAllResolutionHandler findAllResolutionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConstructorWithNoArgs() {
        FindAllResolutionHandler findAllResolutionHandler = new FindAllResolutionHandler();

        assertNotNull(findAllResolutionHandler);
    }

    @Test
    void test_HandleRequest_Should_When_Success() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final String body = ObjectMapperSingletonUtil.objectToJson(SearchResolutionsDTOMock.build());

        DataResolutionResponseDTO data = DataResolutionResponseDTO.builder()
                .pageable(PageableDTO.builder().total(10).build()).build();

        when(iFindAllResolutionsUseCase.invoke(any(SearchResolutionsDTO.class))).thenReturn(data);

        Map<String, String>parameters = new HashMap<>();
        parameters.put("page","1");
        parameters.put("size","10");
        parameters.put("status", null);
        parameters.put("valueCustomFilter", null);

        final ApiGatewayResponseDTO response = findAllResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithQueryParameters(parameters), context
        );

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_AppException_When_Page_Is_Null() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());


        DataResolutionResponseDTO data = DataResolutionResponseDTO.builder()
                .pageable(PageableDTO.builder().total(0).build()).build();

        Map<String, String>parameters = new HashMap<>();
        parameters.put("page", null);
        parameters.put("size","10");
        parameters.put("status", null);
        parameters.put("valueCustomFilter", null);

        when(iFindAllResolutionsUseCase.invoke(any(SearchResolutionsDTO.class))).thenReturn(data);

        final ApiGatewayResponseDTO response = findAllResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithQueryParameters(parameters), context
        );

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_AppException_When_Size_Is_Null() {

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        Map<String, String>parameters = new HashMap<>();
        parameters.put("page", "1");
        parameters.put("size", null);
        parameters.put("status", null);
        parameters.put("valueCustomFilter", null);

        DataResolutionResponseDTO data = DataResolutionResponseDTO.builder()
                .pageable(PageableDTO.builder().total(0).build()).build();

        when(iFindAllResolutionsUseCase.invoke(any(SearchResolutionsDTO.class))).thenReturn(data);

        final ApiGatewayResponseDTO response = findAllResolutionHandler.handleRequest(
                APIGatewayProxyRequestEventMock.buildWithQueryParameters(parameters), context
        );

        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
        clearAllCaches();
    }
}
