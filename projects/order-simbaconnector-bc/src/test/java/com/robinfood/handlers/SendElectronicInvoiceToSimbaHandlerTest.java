package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.response.OrderElectronicBillingDTO;
import com.robinfood.dtos.response.ResponseDTO;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.mocks.requestEventMock;
import com.robinfood.usecase.sendtosimba.ISendMessageToSimbaUseCase;
import com.robinfood.usecase.sendtosimba.SendMessageToSimbaUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.clearAllCaches;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class SendElectronicInvoiceToSimbaHandlerTest {

    @Mock
    private Context context;

    @Mock
    private ISendMessageToSimbaUseCase sendMessageToSimbaUseCase;

    private final Map<String, Object> requestEvent = requestEventMock.getDefault();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Should_HandleRequest_When_Data_Ok() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final OrderElectronicBillingDTO orderElectronicBillingDTO = OrderElectronicBillingDTO.builder()
                .statusCode(200)
                .build();

        when(sendMessageToSimbaUseCase.invoke(any(TransactionRequestDTO.class))).thenReturn(orderElectronicBillingDTO);

        final SendElectronicInvoiceToSimbaHandler eventHandler =
                new SendElectronicInvoiceToSimbaHandler(sendMessageToSimbaUseCase);

        eventHandler.handleRequest(requestEvent, context);
        clearAllCaches();
    }

    @Test
    void test_Should_HandleRequest_When_Status_Code_500_Data_Ok() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        mockStatic(JwtMiddleware.class);
        doNothing().when(JwtMiddleware.class);
        JwtMiddleware.validateToken(Mockito.anyString());

        final OrderElectronicBillingDTO orderElectronicBillingDTO = OrderElectronicBillingDTO.builder()
                .statusCode(500)
                .build();

        when(sendMessageToSimbaUseCase.invoke(any(TransactionRequestDTO.class))).thenReturn(orderElectronicBillingDTO);

        final SendElectronicInvoiceToSimbaHandler eventHandler =
                new SendElectronicInvoiceToSimbaHandler(sendMessageToSimbaUseCase);

        eventHandler.handleRequest(requestEvent, context);
        clearAllCaches();
    }

    @Test
    void test_handleRequest_Should_When_Build_Is_Empty() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        SendElectronicInvoiceToSimbaHandler eventHandler =
                new SendElectronicInvoiceToSimbaHandler(sendMessageToSimbaUseCase);
        Assertions.assertNotNull(eventHandler);
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Should_When_FieldsValidateException() {

        mockStatic(LogsUtil.class);
        doNothing().when(LogsUtil.class);
        LogsUtil.getInstance(context);

        final OrderElectronicBillingDTO orderElectronicBillingDTO = OrderElectronicBillingDTO.builder()
                .statusCode(200)
                .build();

        when(sendMessageToSimbaUseCase.invoke(any(TransactionRequestDTO.class))).thenReturn(orderElectronicBillingDTO);

        final SendElectronicInvoiceToSimbaHandler eventHandler =
                new SendElectronicInvoiceToSimbaHandler(sendMessageToSimbaUseCase);

        final ApiGatewayResponseDTO apiGatewayResponse = eventHandler.handleRequest(requestEvent, context);

        final ResponseDTO response = ObjectMapperSingleton.jsonToClass(apiGatewayResponse.getBody(), ResponseDTO.class);

        Assertions.assertEquals( true, response.getError());
        clearAllCaches();
    }

    @Test
    void test_HandleRequest_Accepted_Should_When_Instance() {

        SendElectronicInvoiceToSimbaHandler sendElectronicInvoiceToSimbaHandler =
                new SendElectronicInvoiceToSimbaHandler();

        Assert.notNull(sendElectronicInvoiceToSimbaHandler);
    }
}
