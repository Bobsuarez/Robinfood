package com.robinfood.usecases.processhandler;

import com.robinfood.constants.HttpStatusConstant;
import com.robinfood.datamock.OrderToCreateDTOMock;
import com.robinfood.datamock.request.APIGatewayRequestEventMock;
import com.robinfood.dtos.request.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.usecases.createorder.CreateOrderUseCase;
import com.robinfood.usecases.gettokenbusinesscapability.GetTokenBusinessCapabilityUseCase;
import com.robinfood.util.LogsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class ProcessHandlerUseCaseTest {

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private GetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_ProcessHandlerUseCase_When_DataOK_Should_MethodInvoke() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn("Token");

        when(createOrderUseCase.invoke(any(OrderToCreateDTO.class), anyString(), anyMap()))
                .thenReturn(OrderToCreateDTOMock.getDataDefault());

        ProcessHandlerUseCase processHandlerUseCase
                = new ProcessHandlerUseCase(createOrderUseCase, getTokenBusinessCapabilityUseCase);

        processHandlerUseCase.invoke(OrderToCreateDTOMock.getDataDefault(), APIGatewayRequestEventMock.getHeaders());

        Mockito.verify(createOrderUseCase).invoke(any(OrderToCreateDTO.class), anyString(), anyMap());
        clearAllCaches();
    }

    @Test
    void test_ProcessHandlerUseCase_When_DataOK_Should_ShowException() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.info(anyString());

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn("Token");

        doThrow(new ApiClientsException(ResponseMapper
                .buildWithError(HttpStatusConstant.SC_NOT_FOUND.getCodeHttp(),
                        "Message",
                        Boolean.TRUE), "Message"))
                .when(createOrderUseCase).invoke(any(), anyString(), anyMap());

        ProcessHandlerUseCase processHandlerUseCase
                = new ProcessHandlerUseCase(createOrderUseCase, getTokenBusinessCapabilityUseCase);

        processHandlerUseCase.invoke(OrderToCreateDTOMock.getDataDefault(), APIGatewayRequestEventMock.getHeaders());

        Mockito.verify(createOrderUseCase).invoke(any(OrderToCreateDTO.class), anyString(), anyMap());
        clearAllCaches();
    }

}