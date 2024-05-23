package com.robinfood.ordereports.repositories.paymentmethod;

import com.robinfood.app.library.comunication.ClientRetroFit;
import com.robinfood.app.library.dto.Result;
import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.app.library.exception.app.NetworkConnectionException;
import com.robinfood.ordereports.dtos.orders.PaymentDetailDTO;
import com.robinfood.ordereports.entities.ApiResponseEntity;
import com.robinfood.ordereports.mocks.PaymentDetailDTOMock;
import com.robinfood.ordereports.network.api.PaymentMethodBcAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodRepositoryTest {


    @Mock
    private PaymentMethodBcAPI paymentMethodBcAPI;

    @InjectMocks
    private PaymentMethodRepository paymentMethodRepository;

    private final String token = "token";

    private final String transactionUuid = "abc-123";

    private final Result result = new Result.Success(
            new ApiResponseEntity<>(
                    200,
                    PaymentDetailDTOMock.getDataDefault(),
                    null,
                    "CO",
                    "OK",
                    "200"
            ));

    @Test
    void test_PaymentMethod_Should_OK_When_DataIsCorrect() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenReturn(result);

            PaymentDetailDTO paymentDetailDTO = paymentMethodRepository.getPaymentMethod(token, transactionUuid);
            Assertions.assertNotNull(paymentDetailDTO);
            verify(paymentMethodBcAPI).getPaymentMethod(anyString(), anyString());
        }
    }

    @Test
    void test_PaymentMethod_Should_When_Data_Is_Error_500() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            NetworkConnectionException exception = new NetworkConnectionException("Generic Error", 500);
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenThrow(exception);

            Assertions.assertThrows(ApplicationException.class, () -> paymentMethodRepository.getPaymentMethod(token, transactionUuid));
        }
    }

    @Test
    void test_PaymentMethod_Should_When_Data_Is_Error_404() throws Exception {
        try (MockedStatic<ClientRetroFit> mockedStatic = mockStatic(ClientRetroFit.class)) {
            NetworkConnectionException exception = new NetworkConnectionException("Not Found", 404);
            mockedStatic.when(() -> ClientRetroFit.safeAPICall(any())).thenThrow(exception);

            PaymentDetailDTO paymentDetailDTO = paymentMethodRepository.getPaymentMethod(token, transactionUuid);
            Assertions.assertEquals(paymentDetailDTO, PaymentDetailDTO.builder().build());
        }
    }
}
